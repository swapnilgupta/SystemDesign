package org.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.parkinglot.account.CustomerInfoPortal;
import org.parkinglot.enums.ParkingSpotType;
import org.parkinglot.spot.ParkingSpot;
import org.parkinglot.vehicle.Vehicle;

public class ParkingFloor {

  private String name;
  private HashMap<String, ParkingSpot> handicappedSpots;
  private HashMap<String, ParkingSpot> compactSpots;
  private HashMap<String, ParkingSpot> largeSpots;
  private HashMap<String, ParkingSpot> motorbikeSpots;
  private HashMap<String, ParkingSpot> electricSpots;
  private HashMap<String, CustomerInfoPortal> infoPortals;
  private ParkingDisplayBoard displayBoard;
  private int freeHandicappedSpotCount;
  private int freeCompactSpotCount;
  private int freeLargeSpotCount;
  private int freeMotorbikeSpotCount;
  private int freeElectricSpotCount;
  private int maxTotalSpots;
  private int freeTotalSpots;

  // Row-based organization for consecutive parking
  private HashMap<Integer, List<ParkingSpot>> rows;

  public ParkingFloor(String name) {
    this.name = name;
    this.handicappedSpots = new HashMap<>();
    this.compactSpots = new HashMap<>();
    this.largeSpots = new HashMap<>();
    this.motorbikeSpots = new HashMap<>();
    this.electricSpots = new HashMap<>();
    this.infoPortals = new HashMap<>();
    this.rows = new HashMap<>();
    this.maxTotalSpots = 0;
    this.freeTotalSpots = 0;
  }

  public void addParkingSpot(ParkingSpot spot) {
    switch (spot.getType()) {
      case HANDICAPPED:
        handicappedSpots.put(spot.getNumber(), spot);
        ++maxTotalSpots;
        ++freeTotalSpots;
        break;
      case COMPACT:
        compactSpots.put(spot.getNumber(), spot);
        ++maxTotalSpots;
        ++freeTotalSpots;
        break;
      case LARGE:
        largeSpots.put(spot.getNumber(), spot);
        ++maxTotalSpots;
        ++freeTotalSpots;
        break;
      case MOTORBIKE:
        motorbikeSpots.put(spot.getNumber(), spot);
        ++maxTotalSpots;
        ++freeTotalSpots;
        break;
      case ELECTRIC:
        electricSpots.put(spot.getNumber(), spot);
        ++maxTotalSpots;
        ++freeTotalSpots;
        break;
      default:
        System.out.println("Wrong parking spot type!");
    }
  }

  public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
    spot.assignVehicle(vehicle);
    switch (spot.getType()) {
      case HANDICAPPED:
        updateDisplayBoardForHandicapped(spot);
        --freeHandicappedSpotCount;
        --freeTotalSpots;
        break;
      case COMPACT:
        updateDisplayBoardForCompact(spot);
        --freeCompactSpotCount;
        --freeTotalSpots;
        break;
      case LARGE:
        updateDisplayBoardForLarge(spot);
        --freeLargeSpotCount;
        --freeTotalSpots;
        break;
      case MOTORBIKE:
        updateDisplayBoardForMotorbike(spot);
        --freeMotorbikeSpotCount;
        --freeTotalSpots;
        break;
      case ELECTRIC:
        updateDisplayBoardForElectric(spot);
        --freeElectricSpotCount;
        --freeTotalSpots;
        break;
      default:
        System.out.println("Wrong parking spot type!");
    }
  }

  private void updateDisplayBoardForElectric(ParkingSpot spot) {
    if (this.displayBoard.getElectricFreeSpot().getNumber() == spot.getNumber()) {
      // find another free electric parking and assign to displayBoard
      for (String key : electricSpots.keySet()) {
        if (electricSpots.get(key).isFree()) {
          this.displayBoard.setElectricFreeSpot(electricSpots.get(key));
        }
      }
      this.displayBoard.showEmptySpotNumber();
    }
  }

  private void updateDisplayBoardForMotorbike(ParkingSpot spot) {
    if (this.displayBoard.getMotorbikeFreeSpot().getNumber() == spot.getNumber()) {
      // find another free motorbike parking and assign to displayBoard
      for (String key : motorbikeSpots.keySet()) {
        if (motorbikeSpots.get(key).isFree()) {
          this.displayBoard.setMotorbikeFreeSpot(motorbikeSpots.get(key));
        }
      }
      this.displayBoard.showEmptySpotNumber();
    }
  }

  private void updateDisplayBoardForLarge(ParkingSpot spot) {
    if (this.displayBoard.getLargeFreeSpot().getNumber() == spot.getNumber()) {
      // find another free large parking and assign to displayBoard
      for (String key : largeSpots.keySet()) {
        if (largeSpots.get(key).isFree()) {
          this.displayBoard.setLargeFreeSpot(largeSpots.get(key));
        }
      }
      this.displayBoard.showEmptySpotNumber();
    }
  }

  private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
    if (this.displayBoard.getHandicappedFreeSpot().getNumber() == spot.getNumber()) {
      // find another free handicapped parking and assign to displayBoard
      for (String key : handicappedSpots.keySet()) {
        if (handicappedSpots.get(key).isFree()) {
          this.displayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
        }
      }
      this.displayBoard.showEmptySpotNumber();
    }
  }

  private void updateDisplayBoardForCompact(ParkingSpot spot) {
    if (this.displayBoard.getCompactFreeSpot().getNumber() == spot.getNumber()) {
      // find another free compact parking and assign to displayBoard
      for (String key : compactSpots.keySet()) {
        if (compactSpots.get(key).isFree()) {
          this.displayBoard.setCompactFreeSpot(compactSpots.get(key));
        }
      }
      this.displayBoard.showEmptySpotNumber();
    }
  }

  public void freeSpot(ParkingSpot spot) {
    spot.removeVehicle();
    switch (spot.getType()) {
      case HANDICAPPED:
        freeHandicappedSpotCount++;
        freeTotalSpots++;
        break;
      case COMPACT:
        freeCompactSpotCount++;
        freeTotalSpots++;
        break;
      case LARGE:
        freeLargeSpotCount++;
        freeTotalSpots++;
        break;
      case MOTORBIKE:
        freeMotorbikeSpotCount++;
        freeTotalSpots++;
        break;
      case ELECTRIC:
        freeElectricSpotCount++;
        freeTotalSpots++;
        break;
      default:
        System.out.println("Wrong parking spot type!");
    }
  }

  // check for all the types of spots if full then return false
  public boolean isFull() {
    return freeTotalSpots == 0;
  }

  // Add parking spot to a specific row
  public void addParkingSpotToRow(ParkingSpot spot, int rowNumber) {
    addParkingSpot(spot);
    if (!rows.containsKey(rowNumber)) {
      rows.put(rowNumber, new ArrayList<>());
    }
    rows.get(rowNumber).add(spot);
  }

  // Check if there are consecutive large spots available in any row
  public boolean hasConsecutiveLargeSpots(int requiredSpots) {
    for (List<ParkingSpot> row : rows.values()) {
      if (hasConsecutiveLargeSpotsInRow(row, requiredSpots)) {
        return true;
      }
    }
    return false;
  }

  // Check if there are consecutive large spots available in a specific row
  private boolean hasConsecutiveLargeSpotsInRow(List<ParkingSpot> row, int requiredSpots) {
    int consecutiveCount = 0;
    for (ParkingSpot spot : row) {
      if (spot.getType() == ParkingSpotType.LARGE && spot.isFree()) {
        consecutiveCount++;
        if (consecutiveCount >= requiredSpots) {
          return true;
        }
      } else {
        consecutiveCount = 0;
      }
    }
    return false;
  }

  // Reserve consecutive large spots for bus parking
  public List<ParkingSpot> reserveConsecutiveLargeSpots(int requiredSpots) {
    for (List<ParkingSpot> row : rows.values()) {
      List<ParkingSpot> consecutiveSpots = getConsecutiveLargeSpotsInRow(row, requiredSpots);
      if (consecutiveSpots.size() >= requiredSpots) {
        return consecutiveSpots;
      }
    }
    return new ArrayList<>();
  }

  // Get consecutive large spots from a specific row
  private List<ParkingSpot> getConsecutiveLargeSpotsInRow(List<ParkingSpot> row,
      int requiredSpots) {
    List<ParkingSpot> consecutiveSpots = new ArrayList<>();
    for (ParkingSpot spot : row) {
      if (spot.getType() == ParkingSpotType.LARGE && spot.isFree()) {
        consecutiveSpots.add(spot);
        if (consecutiveSpots.size() >= requiredSpots) {
          return consecutiveSpots;
        }
      } else {
        consecutiveSpots.clear();
      }
    }
    return consecutiveSpots;
  }
}
