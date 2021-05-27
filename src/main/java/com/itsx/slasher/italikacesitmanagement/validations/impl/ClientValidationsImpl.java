/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations.impl;

import com.itsx.slasher.italikacesitmanagement.validations.ClientValidations;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public class ClientValidationsImpl implements ClientValidations {
    
    public ClientValidationsImpl() {}

    @Override
    public boolean validateClientName(JTextField nameClientField, JLabel nameClientValidation) {
        if ( nameClientField.getText().trim().isEmpty() ) {
            nameClientValidation.setText("Debe llenar este campo");
            return false;
        } else if ( nameClientField.getText().matches("[a-zA-Z ]+") ) {
            nameClientValidation.setText("");
            return true;
        } else if ( nameClientField.getText().length() > 20 ) {
            nameClientValidation.setText("El nombre no puede tener mas de 20 caracteres");
            return false;
        } else {
            nameClientValidation.setText("El nombre no puede contener caracteres especiales o numeros");
            return false;
        }
    }

    @Override
    public boolean validateClientLastName(JTextField lastNameClientField, JLabel lastNameClientValidation) {
        if ( lastNameClientField.getText().trim().isEmpty() ) {
            lastNameClientValidation.setText("Debe llenar este campo");
            return false;
        } else if ( lastNameClientField.getText().matches("[a-zA-Z ]+") ) {
            lastNameClientValidation.setText("");
            return true;
        } else if ( lastNameClientField.getText().length() > 20 ) {
            lastNameClientValidation.setText("El apellido no puede tener mas de 20 caracteres");
            return false;
        } else {
            lastNameClientValidation.setText("El apellido no puede contener caracteres especiales o numeros");
            return false;
        }
    }

    @Override
    public boolean validateClientMotherLastName(JTextField motherLastNameClientField, JLabel motherLastNameClientValidation) {
        if ( motherLastNameClientField.getText().trim().isEmpty() ) {
            motherLastNameClientValidation.setText("Debe llenar este campo");
            return false;
        } else if ( motherLastNameClientField.getText().matches("[a-zA-Z ]+") ) {
            motherLastNameClientValidation.setText("");
            return true;
        } else if ( motherLastNameClientField.getText().length() > 20 ) {
            motherLastNameClientValidation.setText("El apellido materno no puede tener mas de 20 caracteres");
            return false;
        } else {
            motherLastNameClientValidation.setText("El apellido materno no puede contener caracteres especiales o numeros");
            return false;
        }
    }

    @Override
    public boolean validateClientCellphone(JTextField cellphoneClientField, JLabel cellphoneClientValidation) {
        if ( cellphoneClientField.getText().trim().isEmpty() ) {
            cellphoneClientValidation.setText("Debe llenar este campo");
            return false;
        } else if ( !cellphoneClientField.getText().matches("[0-9]+") ) {
            cellphoneClientValidation.setText("Solo puede contener numeros");
            return false;
        } else if ( cellphoneClientField.getText().length() > 10 || cellphoneClientField.getText().length() < 10) {
            cellphoneClientValidation.setText("El numero debe contener 10 digitos");
            return false;
        } else if ( cellphoneClientField.getText().matches("[0-9]+") ) {
            cellphoneClientValidation.setText("");
            return true;
        }  else {
            cellphoneClientValidation.setText("Dato erroneo");
            return false;
        }
    }
    
}
