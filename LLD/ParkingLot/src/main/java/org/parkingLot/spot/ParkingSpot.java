package org.parkingLot.spot;

import org.parkingLot.enums.ParkingSpotType;
import org.parkingLot.vehicle.Vehicle;

public abstract class ParkingSpot {

	private String number;
	private boolean free = true;
	private Vehicle vehicle;
	private ParkingSpotType type;

	public boolean IsFree() {
		return free;
	}

	public ParkingSpot(ParkingSpotType type) {
		this.type = type;
	}

	public boolean assignVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		free = false;
		return false;
	}

	public boolean removeVehicle() {
		this.vehicle = null;
		free = true;
		return true;
	}

	// getters and setters
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ParkingSpotType getType() {
		return type;
	}

	public boolean isFree() {
		return free;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void setType(ParkingSpotType type) {
		this.type = type;
	}


}


