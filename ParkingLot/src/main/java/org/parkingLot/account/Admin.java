package org.parkingLot.account;

public class Admin extends Account {

    public boolean addParkingFloor(ParkingFloor floor) {
        return false;
    }

    public boolean addParkingSpot(String floorName, ParkingSpot spot) {
        return false;
    }

    public boolean addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard) {
        return false;
    }

    public boolean addCustomerInfoPanel(String floorName, CustomerInfoPanel infoPanel) {
        return false;
    }

    public boolean addEntrancePanel(EntrancePanel entrancePanel) {
        return false;
    }

    public boolean addExitPanel(ExitPanel exitPanel) {
        return false;
    }

    @Override
    public boolean resetPassword() {
        return false;
    }
}
