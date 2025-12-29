package org.parkinglot.vehicle;

import org.parkinglot.ParkingTicket;
import org.parkinglot.enums.VehicleType;

public abstract class Vehicle {

  private String licenseNumber;
  private final VehicleType type;
  private ParkingTicket ticket;

  public Vehicle(VehicleType type) {
    this.type = type;
  }

  public void assignTicket(ParkingTicket ticket) {
    this.ticket = ticket;
  }

  // getters and setters
  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public VehicleType getType() {
    return type;
  }

  public ParkingTicket getTicket() {
    return ticket;
  }
}

