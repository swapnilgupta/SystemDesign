package org.parking.actor;
import ParkingFloor;
public class Admin extends Account {
    public boolean addParkingFloor(ParkingFloor floor) {

    }
    public boolean addParkingSpot(String floorName, ParkingSpot spot) {

    }
    public bool addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard) {

    }
    public bool addCustomerInfoPanel(String floorName, CustomerInfoPanel infoPanel);

    public bool addEntrancePanel(EntrancePanel entrancePanel);
    public bool addExitPanel(ExitPanel exitPanel);
}

public class ParkingAttendant extends Account {
    public bool processTicket(string TicketNumber);
}
