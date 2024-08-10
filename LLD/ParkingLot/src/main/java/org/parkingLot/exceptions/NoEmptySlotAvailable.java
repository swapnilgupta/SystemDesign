package org.parkingLot.exceptions;

public class NoEmptySlotAvailable extends Exception {

	public NoEmptySlotAvailable(String message) {
		super(message);
	}
}