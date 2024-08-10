package org.parkingLot.actors;

import org.parkingLot.ParkingDisplayBoard;
import org.parkingLot.ParkingFloor;
import org.parkingLot.account.Account;
import org.parkingLot.panels.CustomerInfoPanel;
import org.parkingLot.panels.EntrancePanel;
import org.parkingLot.panels.ExitPanel;
import org.parkingLot.spot.ParkingSpot;

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
