package org.parkinglotv2.exceptions;

public class InvalidSlotNumberException extends RuntimeException {
    public InvalidSlotNumberException(String message) {
        super(message);
    }
}
