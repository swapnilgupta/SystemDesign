package org.parkinglot.exceptions;


public class ParkingFullException extends Exception {

  public ParkingFullException() {
    super("Parking lot is full");
  }

  public ParkingFullException(String message) {
    super(message);
  }
}
