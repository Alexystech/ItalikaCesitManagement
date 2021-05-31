/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations.impl;

import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.validations.VehicleValidations;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public class VehicleValidationsImpl implements VehicleValidations {
    
    public VehicleValidationsImpl() {}

    @Override
    public boolean validatePlaqueVehicle(JTextField plaqueVehicleField, JLabel plaqueVehicleValidation) {
        if ( plaqueVehicleField.getText().trim().isEmpty() ) {
            plaqueVehicleValidation.setText("Debe llenar este campo");
            return false;
        } else if ( plaqueVehicleField.getText().length() > 7 || plaqueVehicleField.getText().length() < 7 ) {
            plaqueVehicleValidation.setText("La placa debe contener 7 dijitos");
            return false;
        } else if ( !plaqueVehicleField.getText().matches("[a-zA-Z]{3}[0-9]{3}[a-zA-Z0-9]") ) {
            plaqueVehicleValidation.setText("Placa invalida");
            return false;
        } else {
            plaqueVehicleValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateBrandVehicle(JTextField brandVehicleField, JLabel brandVehicleValidation) {
        if ( brandVehicleField.getText().trim().isEmpty() ) {
            brandVehicleValidation.setText("Debe llenar este campo");
            return false;
        } else if ( brandVehicleField.getText().matches("[a-zA-Z ]+") ) {
            brandVehicleValidation.setText("");
            return true;
        } else if ( brandVehicleField.getText().length() > 20 ) {
            brandVehicleValidation.setText("La marca no puede tener mas de 20 caracteres");
            return false;
        } else {
            brandVehicleValidation.setText("La marca no puede contener caracteres especiales o numeros");
            return false;
        }
    }

    @Override
    public boolean validateModelVehicle(JTextField modelVehicleField, JLabel modelVehicleValidation) {
        if ( modelVehicleField.getText().trim().isEmpty() ) {
            modelVehicleValidation.setText("Debe llenar este campo");
            return false;
        } else if ( modelVehicleField.getText().matches("[a-zA-Z0-9 ]+") ) {
            modelVehicleValidation.setText("");
            return true;
        } else if ( modelVehicleField.getText().length() > 20 ) {
            modelVehicleValidation.setText("La marca no puede tener mas de 20 caracteres");
            return false;
        } else {
            modelVehicleValidation.setText("La marca no puede contener caracteres especiales");
            return false;
        }
    }

    @Override
    public boolean validateYearVehicle(JComboBox yearVehicleCBox, JLabel yearVehicleValidation) {
        if ( yearVehicleCBox.getSelectedItem().toString().isEmpty() ) {
            yearVehicleValidation.setText("Seleccione un año");
            return false;
        } else {
            yearVehicleValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateReferencesInOtherEntites(String plaque, WorkService workService) {
        return workService.getAllWorks()
                .stream()
                .anyMatch( item -> item.getVehicle().getPlaque().equals(plaque) );
    }
}
