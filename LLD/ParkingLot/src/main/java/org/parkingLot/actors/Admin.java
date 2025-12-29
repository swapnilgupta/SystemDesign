package org.parkinglot.actors;

import org.parkinglot.ParkingDisplayBoard;
import org.parkinglot.ParkingFloor;
import org.parkinglot.account.Account;
import org.parkinglot.panels.CustomerInfoPanel;
import org.parkinglot.panels.EntrancePanel;
import org.parkinglot.panels.ExitPanel;
import org.parkinglot.spot.ParkingSpot;

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
