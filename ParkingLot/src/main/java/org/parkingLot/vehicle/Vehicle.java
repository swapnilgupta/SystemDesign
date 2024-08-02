package org.parkingLot.vehicle;

import org.parkingLot.ParkingTicket;
import org.parkingLot.enums.VehicleType;

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

