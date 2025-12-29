package org.parkinglot.exceptions;

public class InvalidSlotNumberException extends RuntimeException {

  public InvalidSlotNumberException(String message) {
    super(message);
  }
}
