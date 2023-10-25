package org.parkinglotv2;

import org.parkinglotv2.exceptions.VehicleNotFoundException;

public enum Command implements CommandI {
    create_parking_lot() {
        @Override
        public void executeCommand(String[] details) {
            floor.createParkingSlot(Integer.parseInt(details[1]));
        }
    },

    park {
        @Override
        public void executeCommand(String[] details) {
            try {
                floor.parkVehicle(new Vehicle(details[1], details[2]));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    },

    leave {
        @Override
        public void executeCommand(String[] details) {
            try {
                int slotNumber = floor.unParkVehicle(Integer.parseInt(details[1]));
                System.out.println("Slot number " + slotNumber + " is free");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    },

    status {
        @Override
        public void executeCommand(String[] details) {
            floor.printStatus();
        }
    },

    registration_numbers_for_cars_with_colour {
        @Override
        public void executeCommand(String[] details) throws VehicleNotFoundException {
            floor.getVehicleNumbersByColor(details[1]).forEach(System.out::println);
        }
    },

    slot_numbers_for_cars_with_colour {
        @Override
        public void executeCommand(String[] details) throws VehicleNotFoundException {
            floor.getSlotNumbersByColor(details[1]).forEach(num -> System.out.print(num + ","));
            System.out.println();
        }
    },
    slot_number_for_registration_number {
        @Override
        public void executeCommand(String[] details) {
            try {
                System.out.println(floor.getSlotNumberByVehicleNumber(details[1]));
            } catch (Exception e) {
                System.out.println("Not Found");
            }
        }
    },
    exit() {
        @Override
        public void executeCommand(String[] details) {

        }

        final Parking floor = Parking.getParkingFloor(1);
    };


    Parking floor = Parking.getParkingFloor(1);
}
