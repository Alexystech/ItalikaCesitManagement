/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.validations.impl;

import com.itsx.slasher.italikacesitmanagement.validations.WorkValidations;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;

/**
 *
 * @author defin
 */
public class WorkValidationsImpl implements WorkValidations {
    
    public WorkValidationsImpl() {}

    @Override
    public boolean validateTypeServiceWork(JComboBox typeServiceCBox, JLabel typeServiceWorkValidation) {
        if ( typeServiceCBox.getSelectedItem().toString().isEmpty() ) {
            typeServiceWorkValidation.setText("Seleccione un servicio");
            return false;
        } else {
            typeServiceWorkValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateMechanicWork(JComboBox mechanicCBox, JLabel mechanicWorkValidation) {
        if ( mechanicCBox.getSelectedItem().toString().isEmpty() ) {
            mechanicWorkValidation.setText("Seleccione un servicio");
            return false;
        } else {
            mechanicWorkValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateVehicleWork(JComboBox vehicleCBox, JLabel vehicleWorkValidation) {
        if ( vehicleCBox.getSelectedItem().toString().isEmpty() ) {
            vehicleWorkValidation.setText("Seleccione un servicio");
            return false;
        } else {
            vehicleWorkValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validatePriceWork(JTextField priceServiceField, JLabel priceWorkValidation) {
        if ( priceServiceField.getText().isEmpty() ) {
            priceWorkValidation.setText("Debe llenar este campo");
            return false;
        } else if ( !priceServiceField.getText().matches("[0-9]+.[0-9]+|[0-9]+") ) {
            priceWorkValidation.setText("Se debe ingresar un numero");
            return false;
        } else {
            priceWorkValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateDateReceiveWork(JDateChooser dateReceive, JLabel dateReceiveWorkValidation) {
        if ( dateReceive.getDate() == null ) {
            dateReceiveWorkValidation.setText("Debe llenar este campo");
            return false;
        } else {
            dateReceiveWorkValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateDateSendWork(JDateChooser dateSend, JLabel dateSendWorkValidation) {
        if ( dateSend.getDate() == null ) {
            dateSendWorkValidation.setText("Debe llenar este campo");
            return false;
        } else {
            dateSendWorkValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateClientWork(JComboBox clientCBox, JLabel clientWorkValidation) {
        if ( clientCBox.getSelectedItem().toString().isEmpty() ) {
            clientWorkValidation.setText("Seleccione un servicio");
            return false;
        } else {
            clientWorkValidation.setText("");
            return true;
        }
    }

    @Override
    public boolean validateIssuesWork(JTextArea issuesTPane, JLabel issuesWorkValidation) {
        if ( issuesTPane.getText().isEmpty() ) {
            issuesWorkValidation.setText("Debe agregar las fallas del vehiculo");
            return false;
        } else if ( issuesTPane.getText().length() > 255) {
            issuesWorkValidation.setText("El texto debe ser menor a 256 caracteres");
            return false;
        } else {
            issuesWorkValidation.setText("");
            return true;
        }
    }
    
}
