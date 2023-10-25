package org.parkinglotv2;

import java.util.UUID;

public class Slot {
    private final String id;
    private final int slotNumber;
    private Vehicle parkedVehicle;

    public Slot(String id, int slotNumber) {
        this.id = id;
        this.slotNumber = slotNumber;
    }

    public String getId() {
        return id;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void placeVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
    }

    public boolean isEmpty() {
        return this.parkedVehicle == null;
    }
}
