/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;

/**
 *
 * @author defin
 */
public interface WorkValidations {
    boolean validateTypeServiceWork(JComboBox typeServiceCBox, JLabel typeServiceWorkValidation);
    boolean validateMechanicWork(JComboBox mechanicCBox, JLabel mechanicWorkValidation);
    boolean validateVehicleWork(JComboBox vehicleCBox, JLabel vehicleWorkValidation);
    boolean validatePriceWork(JTextField priceServiceField, JLabel priceWorkValidation);
    boolean validateDateReceiveWork(JDateChooser dateReceive, JLabel dateReceiveWorkValidation);
    boolean validateDateSendWork(JDateChooser dateSend, JLabel dateSendWorkValidation);
    boolean validateClientWork(JComboBox clientCBox, JLabel clientWorkValidation);
    boolean validateIssuesWork(JTextArea issuesTPane, JLabel issuesWorkValidation);
}
