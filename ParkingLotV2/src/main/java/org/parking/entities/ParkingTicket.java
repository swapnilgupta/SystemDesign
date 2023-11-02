package org.parking.entities;

import org.parking.enums.ParkingSpotType;

public class ParkingTicket {
    private String ticketNumber;
    private String issuedAt;
    private String payedAt;
    private ParkingSpotType parkingSpotType;
    private double payedAmount;
    private String vehicleNumber;
    private String parkingSpotNumber;


    public void saveInDB() {
    }

    public String  getTicketNumber() {
        return ticketNumber;
    }
}
