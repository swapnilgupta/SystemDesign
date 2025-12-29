package org.parkinglot.exceptions;

public class NoEmptySlotAvailable extends Exception {

  public NoEmptySlotAvailable(String message) {
    super(message);
  }
}