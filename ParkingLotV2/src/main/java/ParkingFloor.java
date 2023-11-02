import java.util.HashMap;
import java.util.Objects;
import org.parking.entities.CustomerInfoPortal;
import org.parking.entities.Vehicle;
import org.parking.enums.ParkingSpotType;

public class ParkingFloor {
    private String name;
    private HashMap<Integer, HandicappedSpot> handicappedSpots;
    private HashMap<Integer, CompactSpot> compactSpots;
    private HashMap<Integer, LargeSpot> largeSpots;
    private HashMap<Integer, MotorbikeSpot> motorbikeSpots;
    private HashMap<Integer, ElectricSpot> electricSpots;
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
                handicappedSpots.put(spot.getNumber(), (HandicappedSpot) spot);
                break;
            case COMPACT:
                compactSpots.put(spot.getNumber(), (CompactSpot) spot);
                break;
            case LARGE:
                largeSpots.put(spot.getNumber(), (LargeSpot) spot);
                break;
            case MOTORBIKE:
                motorbikeSpots.put(spot.getNumber(), (MotorbikeSpot) spot);
                break;
            case ELECTRIC:
                electricSpots.put(spot.getNumber(), (ElectricSpot) spot);
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
                break;
            case COMPACT:
                updateDisplayBoardForCompact(spot);
                break;
            case LARGE:
                updateDisplayBoardForLarge(spot);
                break;
            case MOTORBIKE:
                updateDisplayBoardForMotorbike(spot);
                break;
            case ELECTRIC:
                updateDisplayBoardForElectric(spot);
                break;
            default:
                System.out.println("Wrong parking spot type!");
        }
    }

    private void updateDisplayBoardForElectric(ParkingSpot spot) {
    }

    private void updateDisplayBoardForMotorbike(ParkingSpot spot) {
    }

    private void updateDisplayBoardForLarge(ParkingSpot spot) {

    }

    private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
        if (Objects.equals(this.displayBoard.getHandicappedFreeSpot(), spot.getNumber())) {
// find another free handicapped parking and assign to displayBoard
            for (Integer key : handicappedSpots.keySet()) {
                if (handicappedSpots.get(key).isFree()) {
                    this.displayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    private void updateDisplayBoardForCompact(ParkingSpot spot) {
        if (Objects.equals(this.displayBoard.getCompactFreeSpot(), spot.getNumber())) {
// find another free compact parking and assign to displayBoard
            for (Integer key : compactSpots.keySet()) {
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

    public boolean isFull() {
        return freeTotalSpots == 0;
    }
}