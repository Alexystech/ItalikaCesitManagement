/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public interface MechanicValidations {
    boolean validateMechanicName(JTextField nameMechanicField, JLabel nameMechanicValidation);
    boolean validateMechanicLastName(JTextField lastNameMechanicField, JLabel lastnameMechanicValidation);
    boolean validateMechanicMotherLastName(JTextField motherLastNameMechanicField, JLabel motherLastNameMechanicValidation);
    boolean validateMechanicSpeciality(JTextField specialityMechanicField, JLabel specialityMechanicValidation);
    boolean validateMechanicCellphone(JTextField cellphoneMechanicField, JLabel cellphoneMechanicValidation);
}
