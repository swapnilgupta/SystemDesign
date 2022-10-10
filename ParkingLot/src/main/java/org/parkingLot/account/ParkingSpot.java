package org.parkingLot.account;

import org.parkingLot.constants.ParkingSpotType;

public abstract class ParkingSpot {
    private String number;
    private boolean free = true;
    private Vehicle vehicle;
    private final ParkingSpotType type;

    public boolean IsFree() {
        return free;
    }

    public ParkingSpot(ParkingSpotType type) {
        this.type = type;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        free = false;
        return false;
    }

    public boolean removeVehicle() {
        this.vehicle = null;
        free = true;
        return true;
    }

}


