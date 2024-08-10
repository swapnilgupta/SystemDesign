package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
enum VehicleType {
    SEDAN, HATCHBACK, SUV
}

class Vehicle {
    String vehicleId;
    VehicleType vehicleType;

    Vehicle(String vehicleId, VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }
}

class User {

}

class Slot {
    Date startTime;
    Date endTime;
    Slot(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

class Branch {
    String branchName;
    Map<VehicleType, Price> prices = new HashMap<>();
    Map<VehicleType, Integer> vehicleCount = new HashMap<>();
    Map<VehicleType, List<String >> vehicleTypeCars = new HashMap<>();

    Map<String, List<Slot >> bookingMap = new HashMap<>();

    List<String > vehicles = new ArrayList<>();

    Branch(String branchName) {
        this.branchName = branchName;
    }

    public void setPrice(VehicleType type, Price price) {
        prices.put(type, price);
    }

    public void addVehicle(Vehicle vehicle) {
        if(vehicles.contains(vehicle.vehicleId)) {
            System.out.println("Invalid vehicle");
            return;
        }
        vehicles.add(vehicle.vehicleId);
        if(vehicleTypeCars.containsKey(vehicle.vehicleType)) {
            List<String > list = vehicleTypeCars.get(vehicle.vehicleType);
            list.add(vehicle.vehicleId);
        } else {
            List<String > list = new ArrayList<>();
            list.add(vehicle.vehicleId);
            vehicleTypeCars.put(vehicle.vehicleType, list);
        }
    }

    public List<String> getVehicleWithType(VehicleType vehicleType) {
        return vehicleTypeCars.get(vehicleType);
    }

    public boolean validateSlotForVehicle(String vehicleId, Slot curSlot) {
        List<Slot> vehicleBookedSlots = this.bookingMap.get(vehicleId);
        if(vehicleBookedSlots == null) return true;
        for(Slot booked : vehicleBookedSlots) {
            if (!(booked.startTime.after(curSlot.endTime) || booked.endTime.before(curSlot.startTime))) {
                return false;
            }
        }
        return true;
    }

    public Price getPrice(VehicleType vehicleType) {
        return prices.get(vehicleType);
    }
}

class Price {
    public double amount;
    Price(double amount) {
        this.amount = amount;
    }
}

class VehicleRentalService {
    Map<String, Branch > branches = new HashMap<>();
    public void addBranch(String branchName) {
        branches.put(branchName, new Branch(branchName));
    }

    public void allocatePrice(String branchName, VehicleType vehicleType, Price price) {
        if(branches.containsKey(branchName)) {
            Branch branch = branches.get(branchName);
            branch.setPrice(vehicleType, price);
        } else {
            // thow error for invalid branch
            System.out.println("Invalid branch");
        }
    }

    // This will add a new vehicle of the given vehicle type in a given existing branch.
    public void addVehicle(String vehicleId, VehicleType vehicleType, String branchName) {
        Vehicle vehicle = new Vehicle(vehicleId, vehicleType);
        Branch branch = branches.get(branchName);
        branch.addVehicle(vehicle);
    }

    //This will be used to rent a vehicle for the given vehicle type for a given time slot defined by
    //Start time and end time. You can expect the start time and endTime to be in hourly slots only.
    public void bookVehicle(VehicleType vehicleType, String startTime, String endTime)
        throws ParseException {
        // 29-02-2020 10:00 AM"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        Slot curSlot = new Slot(dateFormat.parse(startTime), dateFormat.parse(endTime));
        String bookedVehicle = "";
        Price bookingPrice = new Price(Integer.MAX_VALUE);
        String bookingBranchName = "";
        boolean slotFound = false;
        for(String branchName : branches.keySet()) {
            Branch branch = branches.get(branchName);
            List<String > vehicles = branch.getVehicleWithType(vehicleType);
            for(String vehicleId : vehicles) {
                boolean isValidSlot = branch.validateSlotForVehicle(vehicleId, curSlot);
                if(isValidSlot) {
                    slotFound = true;
                    Price price = branch.getPrice(vehicleType);
                    if(bookingPrice.amount > price.amount) {
                        bookingPrice = price;
                        bookedVehicle = vehicleId;
                        bookingBranchName = branchName;
                    }
                }
            }
        }
        if(slotFound) {
            System.out.println(bookedVehicle + " from " + bookingBranchName + " booked.");
            Branch branch = branches.get(bookingBranchName);
            if(branch.bookingMap.containsKey(bookedVehicle)) {
                branch.bookingMap.get(bookedVehicle).add(curSlot);
            } else {
                List<Slot> slotList = new ArrayList<>();
                slotList.add(curSlot);
                branch.bookingMap.put(bookedVehicle, slotList);
            }

        } else {
            System.out.println("No empty slot for type: " + vehicleType.toString());
        }
    }


}

class Admin {

}

public class Main {
    public static void main(String[] args) throws ParseException {
        // Create the VehicleRentalService
        VehicleRentalService rentalService = new VehicleRentalService();

        // Adding branches
        rentalService.addBranch("Vasanth Vihar");
        rentalService.addBranch("Cyber City");

        // Allocating prices
        rentalService.allocatePrice("Vasanth Vihar", VehicleType.SEDAN, new Price(100));
        rentalService.allocatePrice("Vasanth Vihar", VehicleType.HATCHBACK, new Price(80));
        rentalService.allocatePrice("Cyber City", VehicleType.SEDAN, new Price(200));
        rentalService.allocatePrice("Cyber City", VehicleType.HATCHBACK, new Price(50));

        // Adding vehicles
        rentalService.addVehicle("DL 01 MR 9310", VehicleType.SEDAN, "Vasanth Vihar");
        rentalService.addVehicle("DL 01 MR 9311", VehicleType.SEDAN, "Cyber City");
        rentalService.addVehicle("DL 01 MR 9312", VehicleType.HATCHBACK, "Cyber City");

        // Booking vehicles (Implement this logic)
        rentalService.bookVehicle(VehicleType.SEDAN, "29-02-2020 10:00 AM", "29-02-2020 01:00 PM");
        rentalService.bookVehicle(VehicleType.SEDAN, "29-02-2020 02:00 PM", "29-02-2020 03:00 PM");
        rentalService.bookVehicle(VehicleType.SEDAN, "29-02-2020 02:00 PM", "29-02-2020 03:00 PM");

        // Viewing vehicle inventory (Implement this logic)
//        rentalService.viewVehicleInventory("29-02-2020 02:00 PM", "29-02-2020 03:00 PM");
    }
}