package org.cache;

import java.util.*;


/**
 * A multi-level cache system in Java with a pluggable eviction policy. It handles READ and WRITE
 * operations across N levels, promoting cache hits to the top level and evicting entries when
 * levels are full using a defined eviction strategy
 */
class CacheSystem {

    private final CacheManager cacheManager;

    public CacheSystem(int maxLevels, int levelSize) {
        this.cacheManager = new CacheManager(maxLevels, levelSize, new RandomEvictionStrategy());
    }

    public String read(String key) {
        return cacheManager.read(key);
    }

    public void write(String key, String val) {
        boolean isSuccessful = cacheManager.write(key, val);
        if (isSuccessful) {
            System.out.println("Successfully written to cache");
        } else {
            System.out.println("Failed to write to cache");
        }
    }

}

class CacheEntry {

    public String key;
    public String value;

    CacheEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

class CacheLevel {

    private final Map<String, String> keyValueStore;
    private final int capacity;

    CacheLevel(Map<String, String> keyValueStore, int capacity) {
        this.keyValueStore = keyValueStore;
        this.capacity = capacity;
    }

    public String get(String key) {
        if (!keyValueStore.containsKey(key)) {
            return null;
        }
        return keyValueStore.get(key);
    }

    public boolean put(String key, String val) {
        if (keyValueStore.size() < capacity) {
            keyValueStore.put(key, val);
            return true;
        } else {
            return false;
        }
    }

    public Set<String> getKeys() {
        return keyValueStore.keySet();
    }

    // delete random element from the keyValueStore


    public void remove(String key) {
        keyValueStore.remove(key);
    }

    public boolean isEmpty() {
        return keyValueStore.isEmpty();
    }

    public boolean isFull() {
        return keyValueStore.size() == capacity;
    }
}

interface EvictionStrategy {

    CacheEntry evictRandom(CacheLevel cacheLevel);
}

class RandomEvictionStrategy implements EvictionStrategy {

    @Override
    public CacheEntry evictRandom(CacheLevel cacheLevel) {
        if (!cacheLevel.isEmpty()) {
            Random rand = new Random();
            List<String> keys = new ArrayList<>(cacheLevel.getKeys());
            String randomKey = keys.get(rand.nextInt(keys.size()));
            CacheEntry cacheEntry = new CacheEntry(randomKey, cacheLevel.get(randomKey));
            cacheLevel.remove(randomKey);
            return cacheEntry;
        }
        return null;
    }
}

class CacheManager {

    private final List<CacheLevel> cacheLevels;
    private final int maxLevels;
    private final EvictionStrategy evictionStrategy;

    public CacheManager(int maxLevels, int levelSize, EvictionStrategy evictionStrategy) {
        this.maxLevels = maxLevels;
        this.evictionStrategy = evictionStrategy;
        this.cacheLevels = new ArrayList<>();
        // Initialize the cache levels
        this.initializeCacheLevels(levelSize);
    }

    private void initializeCacheLevels(int levelSize) {
        for (int i = 0; i < maxLevels; i++) {
            cacheLevels.add(new CacheLevel(new HashMap<>(), levelSize));
        }
    }

    public boolean write(String key, String val) {
        // try adding from 1st to Nth level whichever found empty
        for (int i = 0; i < maxLevels; i++) {
            CacheLevel level = cacheLevels.get(i);
            CacheEntry cacheEntry = new CacheEntry(key, val);
            while (level.isFull()) {
                // evict random element
                CacheEntry cacheEntryEvicted = evictionStrategy.evictRandom(level);
                level.put(cacheEntry.key, cacheEntry.value);
                cacheEntry = cacheEntryEvicted;
                level = cacheLevels.get(++i);
            }
            // Here, we have got the level with empty space
            if (level.put(cacheEntry.key, cacheEntry.value)) {
                return true;
            }
        }
        return false;
    }

    public String read(String key) {
        for (int i = 0; i < cacheLevels.size(); i++) {
            String value = cacheLevels.get(i).get(key);
            if (value != null) {
                // promote to the top level
                if (i != 0) {
                    // remove from current level
                    cacheLevels.get(i).remove(key);
                    // try adding from 1 to Nth level whichever found empty
                    this.write(key, value);
                }
                return value;
            }
        }
        return null;
    }
}

public class Main {

    public static void main(String[] args) {
        CacheSystem cacheSystem = new CacheSystem(3, 2); // Max 3 levels, each with a capacity of 2.

        // Test writing to cache
        cacheSystem.write("key1", "value1");
        cacheSystem.write("key2", "value2");
        // This should evict key1 and push it to L2
        cacheSystem.write("key3", "value3");

        // Test reading from cache
        System.out.println(cacheSystem.read("key1")); // Should promote key1 to L1 and print value1
        System.out.println(cacheSystem.read("key2")); // Should be found in L1 and print value2
        System.out.println(cacheSystem.read("key3")); // Should be found in L1 and print value3

        // This should evict key2 to L2, and key1 should move to L3 as key2 is promoted back to L1
        cacheSystem.write("key4", "value4");

        // Test to ensure eviction and promotion are working
        System.out.println(
            cacheSystem.read("key1")); // Should print value1 and promote key1 back to L1
        System.out.println(cacheSystem.read("key2")); // Should print value2 from L2
        System.out.println(cacheSystem.read("key3")); // Should print value3 from L1
        System.out.println(cacheSystem.read("key4")); // Should print value4 from L1

        // This writes operation should cause an eviction and creation of a new level since maxLevels is 3
        cacheSystem.write("key5", "value5");
        // Verify that a new level was created and contains the evicted key-value pair
        System.out.println(cacheSystem.read("key2")); // Should be in the new level and print value2
    }
}