/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations.impl;

import com.itsx.slasher.italikacesitmanagement.validations.TypeOfWorkValidations;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public class TypeOfWorkValidationsImpl implements TypeOfWorkValidations {
    
    public TypeOfWorkValidationsImpl() {}

    @Override
    public boolean validateNameTypeOfWork(JTextField nameTWorkField, JLabel nameTWorkValidation) {
        if ( nameTWorkField.getText().trim().isEmpty() ) {
            nameTWorkValidation.setText("Debe llenar este campo");
            return false;
        } else if ( nameTWorkField.getText().matches("[a-zA-Z ]+") ) {
            nameTWorkValidation.setText("");
            return true;
        } else if ( nameTWorkField.getText().length() > 30 ) {
            nameTWorkValidation.setText("El nombre no puede tener mas de 30 caracteres");
            return false;
        } else {
            nameTWorkValidation.setText("El nombre no puede contener caracteres especiales o numeros");
            return false;
        }
    }
    
}
