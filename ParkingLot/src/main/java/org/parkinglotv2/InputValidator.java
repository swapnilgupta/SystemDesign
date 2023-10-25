package org.parkinglotv2;

public class InputValidator {
    public static boolean isValidSlotNumber(int slotNumber) {
        // Add your validation logic here,
        // For example, check if the slot number is greater than 0, can be replaced with actual validations
        return slotNumber > 0;
    }

    public static boolean isValidInput(String inputLine) {
        // Add your validation logic here,
        // For example, check if the input line is not empty, can be replaced with actual validations
        return !inputLine.isEmpty();
    }
}
