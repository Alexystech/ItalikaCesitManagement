/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public interface LoginValidations {
    boolean validateLoginUserName(JTextField userNameLoginField, JLabel userNameLoginValidation);
    boolean validateLoginPassword(JPasswordField passwordLoginField, JLabel passwordLoginValidation);
}
