package org.parking.actor;

import org.parking.structure.ParkingFloor;
import org.parking.structure.ParkingSpot;
import org.parking.structure.ParkingDisplayBoard;
import org.parking.entities.CustomerInfoPortal;
import org.parking.entities.EntrancePanel;
import org.parking.entities.ExitPanel;

public class Admin extends Account {
    public boolean addParkingFloor(ParkingFloor floor) {
        // Implementation
        return false;
    }

    public boolean addParkingSpot(String floorName, ParkingSpot spot) {
        // Implementation
        return false;
    }

    public boolean addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard) {
        // Implementation
        return false;
    }

    public boolean addCustomerInfoPanel(String floorName, CustomerInfoPortal infoPanel) {
        // Implementation
        return false;
    }

    public boolean addEntrancePanel(EntrancePanel entrancePanel) {
        // Implementation
        return false;
    }

    public boolean addExitPanel(ExitPanel exitPanel) {
        // Implementation
        return false;
    }
}

public class ParkingAttendant extends Account {
    public boolean processTicket(String TicketNumber) {
        // Implementation
        return false;
    }
}