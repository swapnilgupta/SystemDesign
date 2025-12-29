package org.parkinglot;

import java.util.HashMap;
import org.parkinglot.constants.Address;
import org.parkinglot.enums.VehicleType;
import org.parkinglot.exceptions.ParkingFullException;
import org.parkinglot.panels.EntrancePanel;
import org.parkinglot.panels.ExitPanel;
import org.parkinglot.vehicle.Vehicle;

public class ParkingLot {

  private String name;
  private Address address;
  private ParkingRate parkingRate;

  private int compactSpotCount;
  private int largeSpotCount;
  private int motorbikeSpotCount;
  private int electricSpotCount;
  private final int maxCompactCount;
  private final int maxLargeCount;
  private final int maxMotorbikeCount;
  private final int maxElectricCount;

  private HashMap<String, EntrancePanel> entrancePanels;
  private HashMap<String, ExitPanel> exitPanels;
  private HashMap<String, ParkingFloor> parkingFloors;

  // all active parking tickets, identified by their ticketNumber
  private HashMap<String, ParkingTicket> activeTickets;

  // singleton ParkingLot to ensure only one object of ParkingLot in the system,
  // all entrance panels will use this object to create new parking ticket:
  // getNewParkingTicket(),
  // similarly exit panels will also use this object to close parking tickets
  private static ParkingLot parkingLot = null;

  // private constructor to restrict for singleton
  private ParkingLot() {
    // Initialize max spot counts (these would normally come from database/config)
    this.maxCompactCount = 100;
    this.maxLargeCount = 50;
    this.maxMotorbikeCount = 30;
    this.maxElectricCount = 20;

    // Initialize current spot counts
    this.compactSpotCount = 0;
    this.largeSpotCount = 0;
    this.motorbikeSpotCount = 0;
    this.electricSpotCount = 0;

    // Initialize collections
    this.entrancePanels = new HashMap<>();
    this.exitPanels = new HashMap<>();
    this.parkingFloors = new HashMap<>();
    this.activeTickets = new HashMap<>();

    // 1. initialize variables: read name, address and parkingRate from database
    // 2. initialize parking floors: read the parking floor map from database,
    // this map should tell how many parking spots are there on each floor. This
    // should also initialize max spot counts too.
    // 3. initialize parking spot counts by reading all active tickets from database
    // 4. initialize entrance and exit panels: read from database
  }

  // static method to get the singleton instance of StockExchange
  public static ParkingLot getInstance() {
    if (parkingLot == null) {
      parkingLot = new ParkingLot();
    }
    return parkingLot;
  }

  // note that the following method is 'synchronized' to allow multiple entrances
  // panels to issue a new parking ticket without interfering with each other
  public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle)
      throws ParkingFullException {
    if (this.isFull(vehicle.getType())) {
      throw new ParkingFullException();
    }
    ParkingTicket ticket = new ParkingTicket();
    vehicle.assignTicket(ticket);
    ticket.saveInDB();
    // if the ticket is successfully saved in the database, we can increment the
    // parking spot count
    this.incrementSpotCount(vehicle.getType());
    this.activeTickets.put(ticket.getTicketNumber(), ticket);
    return ticket;
  }

  public boolean isFull(VehicleType type) {
    // buses need 5 consecutive large spots in the same row
    if (type == VehicleType.BUS) {
      return !hasConsecutiveLargeSpots(5);
    }

    // trucks and vans can only be parked in LargeSpot
    if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
      return largeSpotCount >= maxLargeCount;
    }

    // motorbikes can park in any available spot (motorbike, compact, or large)
    if (type == VehicleType.MOTORBIKE) {
      return (motorbikeSpotCount + compactSpotCount + largeSpotCount) >= (maxMotorbikeCount
          + maxCompactCount
          + maxLargeCount);
    }

    // cars can be parked at compact or large spots
    if (type == VehicleType.CAR) {
      return (compactSpotCount + largeSpotCount) >= (maxCompactCount + maxLargeCount);
    }

    // electric car can be parked at compact, large or electric spots
    return (compactSpotCount + largeSpotCount + electricSpotCount) >= (maxCompactCount
        + maxLargeCount
        + maxElectricCount);
  }

  // increment the parking spot count based on the vehicle type
  private boolean incrementSpotCount(VehicleType type) {
    if (type == VehicleType.BUS) {
      // Bus takes 5 large spots
      largeSpotCount += 5;
    } else if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
      largeSpotCount++;
    } else if (type == VehicleType.MOTORBIKE) {
      // Motorcycles can park in any spot, prefer motorbike spots first
      if (motorbikeSpotCount < maxMotorbikeCount) {
        motorbikeSpotCount++;
      } else if (compactSpotCount < maxCompactCount) {
        compactSpotCount++;
      } else {
        largeSpotCount++;
      }
    } else if (type == VehicleType.CAR) {
      if (compactSpotCount < maxCompactCount) {
        compactSpotCount++;
      } else {
        largeSpotCount++;
      }
    } else { // electric car
      if (electricSpotCount < maxElectricCount) {
        electricSpotCount++;
      } else if (compactSpotCount < maxCompactCount) {
        compactSpotCount++;
      } else {
        largeSpotCount++;
      }
    }
    return true;
  }

  public boolean isFull() {
    for (String key : parkingFloors.keySet()) {
      if (!parkingFloors.get(key).isFull()) {
        return false;
      }
    }
    return true;
  }

  public void addParkingFloor(ParkingFloor floor) {

  }

  public void addEntrancePanel(EntrancePanel entrancePanel) {

  }

  public void addExitPanel(ExitPanel exitPanel) {

  }

  // Check if there are enough consecutive large spots available for buses
  private boolean hasConsecutiveLargeSpots(int requiredSpots) {
    for (String floorKey : parkingFloors.keySet()) {
      ParkingFloor floor = parkingFloors.get(floorKey);
      if (floor.hasConsecutiveLargeSpots(requiredSpots)) {
        return true;
      }
    }
    return false;
  }
}