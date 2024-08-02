package org.parkingLot;

import java.util.HashMap;
import org.parkingLot.account.CustomerInfoPortal;
import org.parkingLot.spot.ParkingSpot;
import org.parkingLot.vehicle.Vehicle;

public class ParkingFloor {

	private String name;
	private HashMap<String, ParkingSpot> handicappedSpots;
	private HashMap<String, ParkingSpot> compactSpots;
	private HashMap<String, ParkingSpot> largeSpots;
	private HashMap<String, ParkingSpot> motorbikeSpots;
	private HashMap<String, ParkingSpot> electricSpots;
	private HashMap<String, CustomerInfoPortal> infoPortals;
	private ParkingDisplayBoard displayBoard;
	private int freeHandicappedSpotCount;
	private int freeCompactSpotCount;
	private int freeLargeSpotCount;
	private int freeMotorbikeSpotCount;
	private int freeElectricSpotCount;
	private int maxTotalSpots;
	private int freeTotalSpots;

	public ParkingFloor(String name) {
		this.name = name;
	}

	public void addParkingSpot(ParkingSpot spot) {
		switch (spot.getType()) {
			case HANDICAPPED:
				handicappedSpots.put(spot.getNumber(), spot);
				++maxTotalSpots;
				++freeTotalSpots;
				break;
			case COMPACT:
				compactSpots.put(spot.getNumber(), spot);
				++maxTotalSpots;
				++freeTotalSpots;
				break;
			case LARGE:
				largeSpots.put(spot.getNumber(), spot);
				++maxTotalSpots;
				++freeTotalSpots;
				break;
			case MOTORBIKE:
				motorbikeSpots.put(spot.getNumber(), spot);
				++maxTotalSpots;
				++freeTotalSpots;
				break;
			case ELECTRIC:
				electricSpots.put(spot.getNumber(), spot);
				++maxTotalSpots;
				++freeTotalSpots;
				break;
			default:
				System.out.println("Wrong parking spot type!");
		}
	}

	public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
		spot.assignVehicle(vehicle);
		switch (spot.getType()) {
			case HANDICAPPED:
				updateDisplayBoardForHandicapped(spot);
				--freeHandicappedSpotCount;
				--freeTotalSpots;
				break;
			case COMPACT:
				updateDisplayBoardForCompact(spot);
				--freeCompactSpotCount;
				--freeTotalSpots;
				break;
			case LARGE:
				updateDisplayBoardForLarge(spot);
				--freeLargeSpotCount;
				break;
			case MOTORBIKE:
				updateDisplayBoardForMotorbike(spot);
				--freeMotorbikeSpotCount;
				--freeTotalSpots;
				break;
			case ELECTRIC:
				updateDisplayBoardForElectric(spot);
				--freeElectricSpotCount;
				--freeTotalSpots;
				break;
			default:
				System.out.println("Wrong parking spot type!");
		}
	}

	private void updateDisplayBoardForElectric(ParkingSpot spot) {
		if (this.displayBoard.getElectricFreeSpot().getNumber() == spot.getNumber()) {
			// find another free electric parking and assign to displayBoard
			for (String key : electricSpots.keySet()) {
				if (electricSpots.get(key).isFree()) {
					this.displayBoard.setElectricFreeSpot(electricSpots.get(key));
				}
			}
			this.displayBoard.showEmptySpotNumber();
		}
	}

	private void updateDisplayBoardForMotorbike(ParkingSpot spot) {
		if (this.displayBoard.getMotorbikeFreeSpot().getNumber() == spot.getNumber()) {
			// find another free motorbike parking and assign to displayBoard
			for (String key : motorbikeSpots.keySet()) {
				if (motorbikeSpots.get(key).isFree()) {
					this.displayBoard.setMotorbikeFreeSpot(motorbikeSpots.get(key));
				}
			}
			this.displayBoard.showEmptySpotNumber();
		}
	}

	private void updateDisplayBoardForLarge(ParkingSpot spot) {

	}

	private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
		if (this.displayBoard.getHandicappedFreeSpot().getNumber() == spot.getNumber()) {
			// find another free handicapped parking and assign to displayBoard
			for (String key : handicappedSpots.keySet()) {
				if (handicappedSpots.get(key).isFree()) {
					this.displayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
				}
			}
			this.displayBoard.showEmptySpotNumber();
		}
	}

	private void updateDisplayBoardForCompact(ParkingSpot spot) {
		if (this.displayBoard.getCompactFreeSpot().getNumber() == spot.getNumber()) {
			// find another free compact parking and assign to displayBoard
			for (String key : compactSpots.keySet()) {
				if (compactSpots.get(key).isFree()) {
					this.displayBoard.setCompactFreeSpot(compactSpots.get(key));
				}
			}
			this.displayBoard.showEmptySpotNumber();
		}
	}

	public void freeSpot(ParkingSpot spot) {
		spot.removeVehicle();
		switch (spot.getType()) {
			case HANDICAPPED:
				freeHandicappedSpotCount++;
				break;
			case COMPACT:
				freeCompactSpotCount++;
				break;
			case LARGE:
				freeLargeSpotCount++;
				break;
			case MOTORBIKE:
				freeMotorbikeSpotCount++;
				break;
			case ELECTRIC:
				freeElectricSpotCount++;
				break;
			default:
				System.out.println("Wrong parking spot type!");
		}
	}

	// check for all the types of spots if full then return false
	public boolean isFull() {
		return freeTotalSpots == 0;
	}
}
