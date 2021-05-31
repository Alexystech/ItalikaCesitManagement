/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations;

import com.itsx.slasher.italikacesitmanagement.service.WorkService;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public interface VehicleValidations {
    boolean validatePlaqueVehicle(JTextField plaqueVehicleField, JLabel plaqueVehicleValidation);
    boolean validateBrandVehicle(JTextField brandVehicleField, JLabel brandVehicleValidation);
    boolean validateModelVehicle(JTextField modelVehicleField, JLabel modelVehicleValidation);
    boolean validateYearVehicle(JComboBox yearVehicleCBox, JLabel yearVehicleValidation);
    boolean validateReferencesInOtherEntites(String plaque, WorkService workService);
}
