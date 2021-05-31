package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.model.TypeOfWork;
import com.itsx.slasher.italikacesitmanagement.service.TypeOfWorkService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.validations.TypeOfWorkValidations;
import com.itsx.slasher.italikacesitmanagement.validations.impl.TypeOfWorkValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.view.TypeOfWorksDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeOfWorkController implements ActionListener {

    private TypeOfWorkService typeOfWorkService;
    private TypeOfWorksDialog typeOfWorksDialog;
    private WorkService workService;

    public TypeOfWorkController(TypeOfWorksDialog typeOfWorksDialog, TypeOfWorkService typeOfWorkService, WorkService workService) {
        this.typeOfWorksDialog = typeOfWorksDialog;
        this.typeOfWorkService = typeOfWorkService;
        this.workService = workService;

        this.typeOfWorksDialog.updateTWorkDialogButton.addActionListener(this);
        this.typeOfWorksDialog.deleteTWorkDialogButton.addActionListener(this);
    }

    public void start() {
        typeOfWorksDialog.setVisible(true);
        refreshTable();
    }

    public void refreshTable() {

        DefaultTableModel model = (DefaultTableModel) this.typeOfWorksDialog.tableTWorkDialog.getModel();

        deleteAllItemsOfTable(typeOfWorksDialog.tableTWorkDialog.getRowCount(), model, 0);

        typeOfWorkService.getAllTypeOfWorks().forEach( item -> {
            String[] content = {
                    item.getFolio().toString(),
                    item.getNameOfWork()
            };
            model.addRow(content);
        });

    }

    private int deleteAllItemsOfTable(int rowCount, DefaultTableModel model, int index) {
        if (rowCount == 0) {
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
         * update type of work dialog button action
         */
        if ( e.getSource() == typeOfWorksDialog.updateTWorkDialogButton ) {

            TypeOfWorkValidations typeOfWorkValidations = new TypeOfWorkValidationsImpl();

            if ( !typeOfWorkValidations.validateNameTypeOfWork(typeOfWorksDialog.nameTWorkDialogField, typeOfWorksDialog.nameTWorkDialogValidation) ||
                typeOfWorksDialog.folioTWorkDialogField.getText().trim().equals("")) {

                JOptionPane.showMessageDialog(null, "Seleccione un tipo de trabajo",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else {

                Long folioSelected = Long.parseLong( typeOfWorksDialog.folioTWorkDialogField.getText() );
                String nameSelected = typeOfWorksDialog.nameTWorkDialogField.getText();

                TypeOfWork typeOfWork = new TypeOfWork(folioSelected, nameSelected);

                typeOfWorkService.updateTypeOfWork(typeOfWork);

                typeOfWorksDialog.folioTWorkDialogField.setText("");
                typeOfWorksDialog.nameTWorkDialogField.setText("");

                refreshTable();

                JOptionPane.showMessageDialog(null, "Informacion actualizada",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        /**
         * delete type of work dialog action
         */
        if ( e.getSource() == typeOfWorksDialog.deleteTWorkDialogButton ) {

            TypeOfWorkValidations typeOfWorkValidations = new TypeOfWorkValidationsImpl();

            if ( !typeOfWorkValidations.validateNameTypeOfWork(typeOfWorksDialog.nameTWorkDialogField, typeOfWorksDialog.nameTWorkDialogValidation) ||
                    typeOfWorksDialog.folioTWorkDialogField.getText().trim().equals("")) {

                JOptionPane.showMessageDialog(null, "Seleccione un tipo de trabajo",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else if (typeOfWorkValidations.validateReferencesInOtherEntities(Long
                    .parseLong(typeOfWorksDialog.folioTWorkDialogField.getText()), workService)) {

                JOptionPane.showMessageDialog(null, "No se puede eliminar este elemento porque hay otra tabla que la esta referenciando",
                        "Error", JOptionPane.ERROR_MESSAGE);

                JOptionPane.showMessageDialog(null, "Elimine los registros que hacen referencia a este item antes de eliminarlo",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            } else {

                long selectedFolio = Long.parseLong(typeOfWorksDialog.folioTWorkDialogField.getText());

                typeOfWorkService.removeTypeOfWorkByFolio(selectedFolio);

                typeOfWorksDialog.folioTWorkDialogField.setText("");
                typeOfWorksDialog.nameTWorkDialogField.setText("");

                refreshTable();

                JOptionPane.showMessageDialog(null, "Registro eliminado",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }

}
