/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations.impl;

import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.validations.MechanicValidations;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public class MechanicValidationsImpl implements MechanicValidations {
    
    public MechanicValidationsImpl() {}

    @Override
    public boolean validateMechanicName(JTextField nameMechanicField, JLabel nameMechanicValidation) {
        if ( nameMechanicField.getText().trim().isEmpty() ) {
            nameMechanicValidation.setText("Debe llenar este campo");
            return false;
        } else if ( nameMechanicField.getText().matches("[a-zA-Z ]+") ) {
            nameMechanicValidation.setText("");
            return true;
        } else if ( nameMechanicField.getText().length() > 20 ) {
            nameMechanicValidation.setText("El nombre no puede tener mas de 20 caracteres");
            return false;
        } else {
            nameMechanicValidation.setText("El nombre no puede contener caracteres especiales o numeros");
            return false;
        }
    }

    @Override
    public boolean validateMechanicLastName(JTextField lastNameMechanicField, JLabel lastNameMechanicValidation) {
        if ( lastNameMechanicField.getText().trim().isEmpty() ) {
            lastNameMechanicValidation.setText("Debe llenar este campo");
            return false;
        } else if ( lastNameMechanicField.getText().matches("[a-zA-Z ]+") ) {
            lastNameMechanicValidation.setText("");
            return true;
        } else if ( lastNameMechanicField.getText().length() > 20 ) {
            lastNameMechanicValidation.setText("El apellido no puede tener mas de 20 caracteres");
            return false;
        } else {
            lastNameMechanicValidation.setText("El paellido no puede contener caracteres especiales o numeros");
            return false;
        }
    }

    @Override
    public boolean validateMechanicMotherLastName(JTextField motherLastNameMechanicField, JLabel motherLastNameMechanicValidation) {
        if ( motherLastNameMechanicField.getText().trim().isEmpty() ) {
            motherLastNameMechanicValidation.setText("Debe llenar este campo");
            return false;
        } else if ( motherLastNameMechanicField.getText().matches("[a-zA-Z ]+") ) {
            motherLastNameMechanicValidation.setText("");
            return true;
        } else if ( motherLastNameMechanicField.getText().length() > 20 ) {
            motherLastNameMechanicValidation.setText("El apellido materno no puede tener mas de 20 caracteres");
            return false;
        } else {
            motherLastNameMechanicValidation.setText("El apellido materno no puede contener caracteres especiales o numeros");
            return false;
        }
    }

    @Override
    public boolean validateMechanicSpeciality(JTextField specialityMechanicField, JLabel specialityMechanicValidation) {
        if ( specialityMechanicField.getText().trim().isEmpty() ) {
            specialityMechanicValidation.setText("Debe llenar este campo");
            return false;
        } else if ( specialityMechanicField.getText().matches("[a-zA-Z ]+") ) {
            specialityMechanicValidation.setText("");
            return true;
        } else if ( specialityMechanicField.getText().length() > 20 ) {
            specialityMechanicValidation.setText("La especialidad no puede tener mas de 20 caracteres");
            return false;
        } else {
            specialityMechanicValidation.setText("La especialidad no puede contener caracteres especiales o numeros");
            return false;
        }
    }

    @Override
    public boolean validateMechanicCellphone(JTextField cellphoneMechanicField, JLabel cellphoneMechanicValidation) {
        if ( cellphoneMechanicField.getText().trim().isEmpty() ) {
            cellphoneMechanicValidation.setText("Debe llenar este campo");
            return false;
        } else if ( !cellphoneMechanicField.getText().matches("[0-9]+") ) {
            cellphoneMechanicValidation.setText("Solo puede contener numeros");
            return false;
        } else if ( cellphoneMechanicField.getText().length() > 10 || cellphoneMechanicField.getText().length() < 10) {
            cellphoneMechanicValidation.setText("El numero debe contener 10 digitos");
            return false;
        } else if ( cellphoneMechanicField.getText().matches("[0-9]+") ) {
            cellphoneMechanicValidation.setText("");
            return true;
        }  else {
            cellphoneMechanicValidation.setText("Dato erroneo");
            return false;
        }
    }

    @Override
    public boolean validateReferencesInOtherEntities(long folio, WorkService workService) {
        return workService.getAllWorks()
                .stream()
                .anyMatch( item -> item.getMechanic().getFolio() == folio );
    }
}
