/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service;

import com.itsx.slasher.italikacesitmanagement.model.Vehicle;
import java.util.List;

/**
 *
 * @author defin
 */
public interface VehicleService {
    boolean createVehicle(Vehicle vehicle);
    boolean removeVehicleByPlaque(String plaque);
    boolean updateVehicle(Vehicle vehicle);
    Vehicle getVehicleByPlaque(String plaque);
    List<Vehicle> getAllVehicles();
}
