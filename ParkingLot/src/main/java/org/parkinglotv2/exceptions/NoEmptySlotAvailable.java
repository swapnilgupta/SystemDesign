package org.parkinglotv2.exceptions;

public class NoEmptySlotAvailable extends Exception {
    public NoEmptySlotAvailable(String message) {
        super(message);
    }
}