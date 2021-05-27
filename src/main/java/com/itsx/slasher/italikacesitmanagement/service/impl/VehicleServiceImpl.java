/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service.impl;

import com.itsx.slasher.italikacesitmanagement.model.Vehicle;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;
import java.util.List;

/**
 *
 * @author defin
 */
public class VehicleServiceImpl implements VehicleService{

    @Override
    public boolean createVehicle(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean removeVehicleByPlaque(String plaque) {
        return false;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        return false;
    }

    @Override
    public Vehicle getVehicleByPlaque(String plaque) {
        return null;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return null;
    }
}
