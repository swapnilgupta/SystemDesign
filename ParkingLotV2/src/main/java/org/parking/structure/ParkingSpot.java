package org.parking.structure;

import org.parking.entities.Vehicle;
import org.parking.enums.ParkingSpotType;

public abstract class ParkingSpot {
    private Integer number;
    private boolean free;
    private Vehicle vehicle;
    private final ParkingSpotType type;

    public boolean IsFree() {
        return false;
    }

    public ParkingSpot(ParkingSpotType type) {
        this.type = type;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        free = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        free = true;
    }

    public ParkingSpotType getType() {
        return type;
    }

    public Integer getNumber() {
        return number;
    }

    public boolean isFree() {
        return free;
    }
}

