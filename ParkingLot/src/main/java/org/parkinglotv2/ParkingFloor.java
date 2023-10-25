package org.parkinglotv2;

import java.util.List;
import org.parkinglotv2.exceptions.NoEmptySlotAvailable;
import org.parkinglotv2.exceptions.VehicleNotFoundException;

public interface ParkingFloor {
    static Parking getParkingFloor(int floorNumber) {
        return null;
    }

    static void clearAll() {
    }

    boolean createParkingSlot(int numberOfSlots);

    boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable;

    int unParkVehicle(int slotNumber);

    void printStatus();

    List<String> getVehicleNumbersByColor(String color) throws VehicleNotFoundException;

    List<Integer> getSlotNumbersByColor(String color) throws VehicleNotFoundException;

    Integer getSlotNumberByVehicleNumber(String vehicleNumber) throws VehicleNotFoundException;
}