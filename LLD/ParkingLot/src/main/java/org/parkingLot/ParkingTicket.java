package org.parkingLot;

import org.parkingLot.enums.ParkingTicketStatus;

public class ParkingTicket {
	private String ticketNumber;
	private long issuedAt;
	private long payedAt;
	private long payedAmount;
	private ParkingTicketStatus status;


	public void saveInDB() {
	}

	// getter and setter
	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public long getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(long issuedAt) {
		this.issuedAt = issuedAt;
	}

	public long getPayedAt() {
		return payedAt;
	}

	public void setPayedAt(long payedAt) {
		this.payedAt = payedAt;
	}

	public long getPayedAmount() {
		return payedAmount;
	}

	public void setPayedAmount(long payedAmount) {
		this.payedAmount = payedAmount;
	}

}
