/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations.impl;

import com.itsx.slasher.italikacesitmanagement.validations.LoginValidations;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public class LoginValidationsImpl implements LoginValidations {
    
    public LoginValidationsImpl() {}

    @Override
    public boolean validateLoginUserName(JTextField userNameLoginField, JLabel userNameLoginValidation) {
        if ( userNameLoginField.getText().trim().isEmpty() ) {
            userNameLoginValidation.setText("Dabe llenar este campo");
            return false;
        } else {
            userNameLoginValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateLoginPassword(JPasswordField passwordLoginField, JLabel passwordLoginValidation) {
        char[] x = new char[0];
        if (Arrays.equals(passwordLoginField.getPassword(), x) ) {
            passwordLoginValidation.setText("Debe llenar este campo");
            return false;
        } else {
            passwordLoginValidation.setText("");
            return true;
        }
    }
    
}
