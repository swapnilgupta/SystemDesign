package org.parkinglot;

import org.parkinglot.spot.CompactSpot;
import org.parkinglot.spot.ElectricSpot;
import org.parkinglot.spot.HandicappedSpot;
import org.parkinglot.spot.LargeSpot;
import org.parkinglot.spot.MotorbikeSpot;
import org.parkinglot.spot.ParkingSpot;

public class ParkingDisplayBoard {

  private String id;
  private HandicappedSpot handicappedFreeSpot;
  private CompactSpot compactFreeSpot;
  private LargeSpot largeFreeSpot;
  private MotorbikeSpot motorbikeFreeSpot;
  private ElectricSpot electricFreeSpot;

  public void showEmptySpotNumber() {
    String message = "";
    if (handicappedFreeSpot.IsFree()) {
      message += "Free Handicapped: " + handicappedFreeSpot.getNumber();
    } else {
      message += "Handicapped is full";
    }
    message += System.lineSeparator();

    if (compactFreeSpot.IsFree()) {
      message += "Free Compact: " + compactFreeSpot.getNumber();
    } else {
      message += "Compact is full";
    }
    message += System.lineSeparator();

    if (largeFreeSpot.IsFree()) {
      message += "Free Large: " + largeFreeSpot.getNumber();
    } else {
      message += "Large is full";
    }
    message += System.lineSeparator();

    if (motorbikeFreeSpot.IsFree()) {
      message += "Free Motorbike: " + motorbikeFreeSpot.getNumber();
    } else {
      message += "Motorbike is full";
    }
    message += System.lineSeparator();

    if (electricFreeSpot.IsFree()) {
      message += "Free Electric: " + electricFreeSpot.getNumber();
    } else {
      message += "Electric is full";
    }

    System.out.println(message);
  }

  // Getters
  public HandicappedSpot getHandicappedFreeSpot() {
    return handicappedFreeSpot;
  }

  public CompactSpot getCompactFreeSpot() {
    return compactFreeSpot;
  }

  public LargeSpot getLargeFreeSpot() {
    return largeFreeSpot;
  }

  public MotorbikeSpot getMotorbikeFreeSpot() {
    return motorbikeFreeSpot;
  }

  public ElectricSpot getElectricFreeSpot() {
    return electricFreeSpot;
  }

  // Setters
  public void setHandicappedFreeSpot(ParkingSpot spot) {
    if (spot instanceof HandicappedSpot) {
      this.handicappedFreeSpot = (HandicappedSpot) spot;
    }
  }

  public void setCompactFreeSpot(ParkingSpot spot) {
    if (spot instanceof CompactSpot) {
      this.compactFreeSpot = (CompactSpot) spot;
    }
  }

  public void setLargeFreeSpot(ParkingSpot spot) {
    if (spot instanceof LargeSpot) {
      this.largeFreeSpot = (LargeSpot) spot;
    }
  }

  public void setMotorbikeFreeSpot(ParkingSpot spot) {
    if (spot instanceof MotorbikeSpot) {
      this.motorbikeFreeSpot = (MotorbikeSpot) spot;
    }
  }

  public void setElectricFreeSpot(ParkingSpot spot) {
    if (spot instanceof ElectricSpot) {
      this.electricFreeSpot = (ElectricSpot) spot;
    }
  }
}
