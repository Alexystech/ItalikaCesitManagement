package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.model.Mechanic;
import com.itsx.slasher.italikacesitmanagement.service.MechanicService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.validations.MechanicValidations;
import com.itsx.slasher.italikacesitmanagement.validations.impl.MechanicValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.view.MechanicDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MechanicController implements ActionListener {

    private MechanicService mechanicService;
    private MechanicDialog mechanicDialog;
    private WorkService workService;

    public MechanicController(MechanicDialog mechanicDialog, MechanicService mechanicService, WorkService workService) {

        this.mechanicDialog = mechanicDialog;
        this.mechanicService = mechanicService;
        this.workService = workService;

        this.mechanicDialog.updateMechanicDialogButton.addActionListener(this);
        this.mechanicDialog.deleteMechanicDialogButton.addActionListener(this);

    }

    public void start() {
        runValidations();
        refreshTable();
        mechanicDialog.setVisible(true);
    }

    public void runValidations() {

        mechanicDialog.nameMechanicDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();

                mechanicValidations.validateMechanicName(mechanicDialog.nameMechanicDialogField
                        , mechanicDialog.nameMechanicDialogValidation);
            }
        });

        mechanicDialog.lastnameMechanicDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();

                mechanicValidations.validateMechanicLastName(mechanicDialog.lastnameMechanicDialogField
                        , mechanicDialog.lastnameMechanicDialogValidation);
            }
        });

        mechanicDialog.motherLastnameMechanicDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();

                mechanicValidations.validateMechanicMotherLastName(mechanicDialog.motherLastnameMechanicDialogField
                        , mechanicDialog.motherLastnameMechanicDialogValidation);
            }
        });

        mechanicDialog.specialityMechanicDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();

                mechanicValidations.validateMechanicSpeciality(mechanicDialog.specialityMechanicDialogField
                        , mechanicDialog.specialityMechanicDialogValidation);
            }
        });

        mechanicDialog.cellphoneMechanicDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();

                mechanicValidations.validateMechanicCellphone(mechanicDialog.cellphoneMechanicDialogField
                        , mechanicDialog.cellphoneMechanicDialogValidation);
            }
        });

    }

    public void refreshTable() {

        DefaultTableModel model = (DefaultTableModel) mechanicDialog.tableMechanicDialog.getModel();

        deleteAllItemsOfTable(mechanicDialog.tableMechanicDialog.getRowCount(), model, 0);

        mechanicService.getAllMechanics().forEach( item -> {
            String[] content = {
                    item.getFolio().toString(),
                    item.getName(),
                    item.getLastName(),
                    item.getMotherLastName(),
                    item.getSpeciality(),
                    item.getCellphone()
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
         * update mechanic dialog action button
         */
        if ( e.getSource() == mechanicDialog.updateMechanicDialogButton ) {

            MechanicValidations mechanicValidations = new MechanicValidationsImpl();

            if ( !mechanicValidations.validateMechanicName(mechanicDialog.nameMechanicDialogField, mechanicDialog.nameMechanicDialogValidation) ||
                !mechanicValidations.validateMechanicLastName(mechanicDialog.lastnameMechanicDialogField, mechanicDialog.lastnameMechanicDialogValidation) ||
                !mechanicValidations.validateMechanicMotherLastName(mechanicDialog.motherLastnameMechanicDialogField, mechanicDialog.motherLastnameMechanicDialogValidation) ||
                !mechanicValidations.validateMechanicSpeciality(mechanicDialog.specialityMechanicDialogField, mechanicDialog.specialityMechanicDialogValidation) ||
                !mechanicValidations.validateMechanicCellphone(mechanicDialog.cellphoneMechanicDialogField, mechanicDialog.cellphoneMechanicDialogValidation) ||
                mechanicDialog.folioMechanicDialogField.getText().trim().equals("") ) {

                JOptionPane.showMessageDialog(null, "Seleccione un mecanico",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else {

                Long selectedFolio = Long.parseLong(mechanicDialog.folioMechanicDialogField.getText());
                String selectedName = mechanicDialog.nameMechanicDialogField.getText();
                String selectedLastname = mechanicDialog.lastnameMechanicDialogField.getText();
                String selectedMoterLastname = mechanicDialog.motherLastnameMechanicDialogField.getText();
                String selectedSpeciality = mechanicDialog.specialityMechanicDialogField.getText();
                String selectedCellphone = mechanicDialog.cellphoneMechanicDialogField.getText();

                Mechanic mechanic = new Mechanic(selectedFolio, selectedName, selectedLastname
                        , selectedMoterLastname, selectedSpeciality, selectedCellphone);

                mechanicService.updateMechanic(mechanic);

                mechanicDialog.folioMechanicDialogField.setText("");
                mechanicDialog.nameMechanicDialogField.setText("");
                mechanicDialog.lastnameMechanicDialogField.setText("");
                mechanicDialog.motherLastnameMechanicDialogField.setText("");
                mechanicDialog.specialityMechanicDialogField.setText("");
                mechanicDialog.cellphoneMechanicDialogField.setText("");

                refreshTable();

                JOptionPane.showMessageDialog(null, "Informacion actualizada",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        /**
         * delete mechanic action button
         */
        if ( e.getSource() == mechanicDialog.deleteMechanicDialogButton ) {

            MechanicValidations mechanicValidations = new MechanicValidationsImpl();

            if ( !mechanicValidations.validateMechanicName(mechanicDialog.nameMechanicDialogField, mechanicDialog.nameMechanicDialogValidation) ||
                !mechanicValidations.validateMechanicLastName(mechanicDialog.lastnameMechanicDialogField, mechanicDialog.lastnameMechanicDialogValidation) ||
                !mechanicValidations.validateMechanicMotherLastName(mechanicDialog.motherLastnameMechanicDialogField, mechanicDialog.motherLastnameMechanicDialogValidation) ||
                !mechanicValidations.validateMechanicSpeciality(mechanicDialog.specialityMechanicDialogField, mechanicDialog.specialityMechanicDialogValidation) ||
                !mechanicValidations.validateMechanicCellphone(mechanicDialog.cellphoneMechanicDialogField, mechanicDialog.cellphoneMechanicDialogValidation) ||
                mechanicDialog.folioMechanicDialogField.getText().trim().equals("") ) {

                JOptionPane.showMessageDialog(null, "Seleccione un mecanico",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else if ( mechanicValidations.validateReferencesInOtherEntities(Long
                    .parseLong(mechanicDialog.folioMechanicDialogField.getText()), workService) ) {

                JOptionPane.showMessageDialog(null, "No se puede eliminar este elemento porque hay otra tabla que la esta referenciando",
                        "Error", JOptionPane.ERROR_MESSAGE);

                JOptionPane.showMessageDialog(null, "Elimine los registros que hacen referencia a este item antes de eliminarlo",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            } else {

                long selectedFolio = Long.parseLong(mechanicDialog.folioMechanicDialogField.getText());

                mechanicService.removeMechanicByFolio(selectedFolio);

                mechanicDialog.folioMechanicDialogField.setText("");
                mechanicDialog.nameMechanicDialogField.setText("");
                mechanicDialog.lastnameMechanicDialogField.setText("");
                mechanicDialog.motherLastnameMechanicDialogField.setText("");
                mechanicDialog.specialityMechanicDialogField.setText("");
                mechanicDialog.cellphoneMechanicDialogField.setText("");

                refreshTable();

                JOptionPane.showMessageDialog(null, "Registro eliminado",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }
}
