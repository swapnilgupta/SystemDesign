package org.parkingLot;

import org.parkingLot.spot.CompactSpot;
import org.parkingLot.spot.ElectricSpot;
import org.parkingLot.spot.HandicappedSpot;
import org.parkingLot.spot.LargeSpot;
import org.parkingLot.spot.MotorbikeSpot;
import org.parkingLot.spot.ParkingSpot;

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

	public ParkingSpot getElectricFreeSpot() {

	}
}
