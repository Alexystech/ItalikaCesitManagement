/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations;

import com.itsx.slasher.italikacesitmanagement.service.WorkService;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author defin
 */
public interface ClientValidations {
    boolean validateClientName(JTextField nameClientField, JLabel nameClientValidation);
    boolean validateClientLastName(JTextField lastNameClientField, JLabel lastNameClientValidation);
    boolean validateClientMotherLastName(JTextField motherLastNameClientField, JLabel motherLastNameClientValidation);
    boolean validateClientCellphone(JTextField cellphoneClientField, JLabel cellphoneClientValidation);
    boolean validateReferencesInOtherEntities(long folio, WorkService workService);
}
