package org.parkinglotv2;

import org.parkinglotv2.exceptions.VehicleNotFoundException;

public interface CommandI {
    void executeCommand(String[] details) throws VehicleNotFoundException;
}
