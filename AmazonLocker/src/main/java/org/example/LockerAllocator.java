package org.example;

import java.util.ArrayList;

public class LockerAllocator {
    private ArrayList<Integer> availableList;
    private ArrayList<Integer> busyList;

    private int getCode() {
        // logic to generate the 4 digit code
        int code = 1234;
        saveCodeToDB(1234);
        return code;
    }

    private int getLocker(Location userLocation) {
        int id = -1;
        int id = availableList.stream().sorted().findFirst().get();
        return id;
    }

    private void saveCodeToDB(int i) {
        // logic to save the code to db
    }


}
