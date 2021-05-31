package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.model.Vehicle;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.validations.VehicleValidations;
import com.itsx.slasher.italikacesitmanagement.validations.impl.VehicleValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.view.VehicleDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VehicleController implements ActionListener {

    private VehicleService vehicleService;
    private VehicleDialog vehicleDialog;
    private WorkService workService;

    public VehicleController(VehicleDialog vehicleDialog, VehicleService vehicleService, WorkService workService) {
        this.vehicleDialog = vehicleDialog;
        this.vehicleService = vehicleService;
        this.workService = workService;

        this.vehicleDialog.updateVehicleDialogButton.addActionListener(this);
        this.vehicleDialog.deleteVehicleDialogButton.addActionListener(this);
    }

    public void start() {
        vehicleDialog.setVisible(true);
        runValidations();
        refreshTable();
    }

    public void runValidations() {

        vehicleDialog.brandVehicleDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                VehicleValidations vehicleValidations = new VehicleValidationsImpl();

                vehicleValidations.validateBrandVehicle(vehicleDialog.brandVehicleDialogField
                        , vehicleDialog.brandVehicleDialogValidation);
            }
        });

        vehicleDialog.modelVehicleDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                VehicleValidations vehicleValidations = new VehicleValidationsImpl();

                vehicleValidations.validateModelVehicle(vehicleDialog.modelVehicleDialogField
                        , vehicleDialog.modelVehicleDialogValidation);
            }
        });

        vehicleDialog.yearVehicleDialogCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                VehicleValidations vehicleValidations = new VehicleValidationsImpl();

                vehicleValidations.validateYearVehicle(vehicleDialog.yearVehicleDialogCBox
                        , vehicleDialog.yearVehicleDialogValidation);
            }
        });

    }

    private void refreshTable() {

        DefaultTableModel model = (DefaultTableModel) this.vehicleDialog.tableVehicleDialog.getModel();

        deleteAllItemsOfTable(vehicleDialog.tableVehicleDialog.getRowCount(), model, 0);

        vehicleService.getAllVehicles().forEach( item -> {
            String[] content = {
                    item.getPlaque(),
                    item.getBrand(),
                    item.getModel(),
                    item.getYear().toString()
            };
            model.addRow(content);
        });

    }

    private int deleteAllItemsOfTable(int rowCount, DefaultTableModel model, int index) {
        if ( rowCount == 0 ) {
            return 0;
        } else {
            model.removeRow(index);
            deleteAllItemsOfTable(model.getRowCount(), model, index);
            return 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * update action button
         */
        if ( e.getSource() == vehicleDialog.updateVehicleDialogButton ) {

            VehicleValidations vehicleValidations = new VehicleValidationsImpl();

            if ( !vehicleValidations.validateBrandVehicle(vehicleDialog.brandVehicleDialogField, vehicleDialog.brandVehicleDialogValidation) ||
                !vehicleValidations.validateModelVehicle(vehicleDialog.modelVehicleDialogField, vehicleDialog.modelVehicleDialogValidation) ||
                !vehicleValidations.validateYearVehicle(vehicleDialog.yearVehicleDialogCBox, vehicleDialog.yearVehicleDialogValidation) ||
                vehicleDialog.plaqueVehicleDialogField.getText().trim().equals("")) {

                JOptionPane.showMessageDialog(null, "Seleccione un vehiculo",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else {

                String selectedPlaque = vehicleDialog.plaqueVehicleDialogField.getText();
                String selectedBrand = vehicleDialog.brandVehicleDialogField.getText();
                String selectedModel = vehicleDialog.modelVehicleDialogField.getText();
                int selectedYear = Integer.parseInt(vehicleDialog.yearVehicleDialogCBox.getSelectedItem().toString());

                Vehicle vehicle = new Vehicle(selectedPlaque, selectedBrand, selectedModel, selectedYear);

                vehicleService.updateVehicle(vehicle);

                vehicleDialog.plaqueVehicleDialogField.setText("");
                vehicleDialog.brandVehicleDialogField.setText("");
                vehicleDialog.modelVehicleDialogField.setText("");
                vehicleDialog.yearVehicleDialogCBox.setSelectedIndex(0);

                refreshTable();

                JOptionPane.showMessageDialog(null, "Informacion actualizada",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        /**
         * delete action button
         */
        if ( e.getSource() == vehicleDialog.deleteVehicleDialogButton ) {

            VehicleValidations vehicleValidations = new VehicleValidationsImpl();

            if ( !vehicleValidations.validateBrandVehicle(vehicleDialog.brandVehicleDialogField, vehicleDialog.brandVehicleDialogValidation) ||
                 !vehicleValidations.validateModelVehicle(vehicleDialog.modelVehicleDialogField, vehicleDialog.modelVehicleDialogValidation) ||
                 !vehicleValidations.validateYearVehicle(vehicleDialog.yearVehicleDialogCBox, vehicleDialog.yearVehicleDialogValidation) ||
                 vehicleDialog.plaqueVehicleDialogField.getText().trim().equals("") ) {

                JOptionPane.showMessageDialog(null, "Seleccione un vehiculo",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else if ( vehicleValidations.validateReferencesInOtherEntites(vehicleDialog.plaqueVehicleDialogField.getText(), workService) ) {

                JOptionPane.showMessageDialog(null, "No se puede eliminar este elemento porque hay otra tabla que lo esta referenciando",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

                JOptionPane.showMessageDialog(null, "Elimine los registros que hacen referencia a este item antes de eliminarlo",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            } else {

                String selectedPlaque = vehicleDialog.plaqueVehicleDialogField.getText();

                vehicleService.removeVehicleByPlaque(selectedPlaque);

                vehicleDialog.plaqueVehicleDialogField.setText("");
                vehicleDialog.brandVehicleDialogValidation.setText("");
                vehicleDialog.modelVehicleDialogValidation.setText("");
                vehicleDialog.yearVehicleDialogCBox.setSelectedIndex(0);

                refreshTable();

                JOptionPane.showMessageDialog(null, "Registro eliminado",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }
}
