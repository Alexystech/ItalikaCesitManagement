package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.model.Client;
import com.itsx.slasher.italikacesitmanagement.model.Mechanic;
import com.itsx.slasher.italikacesitmanagement.model.TypeOfWork;
import com.itsx.slasher.italikacesitmanagement.model.Vehicle;
import com.itsx.slasher.italikacesitmanagement.model.Work;
import com.itsx.slasher.italikacesitmanagement.service.ClientService;
import com.itsx.slasher.italikacesitmanagement.service.MechanicService;
import com.itsx.slasher.italikacesitmanagement.service.TypeOfWorkService;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.validations.WorkValidations;
import com.itsx.slasher.italikacesitmanagement.validations.impl.WorkValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.view.WorkDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;

public class WorkController implements ActionListener {

    private WorkService workService;
    private WorkDialog workDialog;
    private TypeOfWorkService typeOfWorkService;
    private MechanicService mechanicService;
    private VehicleService vehicleService;
    private ClientService clientService;

    public WorkController(WorkService workService, WorkDialog workDialog
            , TypeOfWorkService typeOfWorkService, VehicleService vehicleService
            , MechanicService mechanicService, ClientService clientService) {
        this.workService = workService;
        this.workDialog = workDialog;
        this.typeOfWorkService = typeOfWorkService;
        this.mechanicService = mechanicService;
        this.vehicleService = vehicleService;
        this.clientService = clientService;

        workDialog.deleteWorkDialogButton.addActionListener(this);
        workDialog.updateWorkDialogButton.addActionListener(this);
    }

    public void start() {
        workDialog.setVisible(true);
        runValidations();
        refreshList();
        refreshTable();
    }

    public void runValidations() {

        workDialog.typeOfWorkDialogCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();

                workValidations.validateTypeServiceWork(workDialog.typeOfWorkDialogCBox
                        , workDialog.folioWorkDialogValidation);
            }
        });

        workDialog.mechanicWorkDialogCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();

                workValidations.validateMechanicWork(workDialog.mechanicWorkDialogCBox
                        , workDialog.mechanicWorkDialogValidation);
            }
        });

        workDialog.vehicleWorkDialogCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();

                workValidations.validateVehicleWork(workDialog.vehicleWorkDialogCBox
                        , workDialog.vehicleWorkDialogValidation);
            }
        });

        workDialog.priceWorkDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();

                workValidations.validatePriceWork(workDialog.priceWorkDialogField
                        , workDialog.priceWorkDialogValidation);
            }
        });

        workDialog.clientWorkDialogCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();

                workValidations.validateClientWork(workDialog.clientWorkDialogCBox
                        , workDialog.clientWorkDialogValidation);
            }
        });

        workDialog.dateReceiveWorkDialogDChooser.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();

                workValidations.validateDateReceiveWork(workDialog.dateReceiveWorkDialogDChooser
                        , workDialog.dateReceibeWorkDialogValidation);
            }
        });

        workDialog.dateSendWorkDialogDChooser.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();

                workValidations.validateDateSendWork(workDialog.dateSendWorkDialogDChooser
                        , workDialog.dateSendWorkDialogValidation);
            }
        });

        workDialog.issuesWorkDialogTArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();

                workValidations.validateIssuesWork(workDialog.issuesWorkDialogTArea
                        , workDialog.issuesWorkDialogValidation);
            }
        });

    }

    private void refreshList() {

        workDialog.typeOfWorkDialogCBox.removeAllItems();
        workDialog.mechanicWorkDialogCBox.removeAllItems();
        workDialog.vehicleWorkDialogCBox.removeAllItems();
        workDialog.clientWorkDialogCBox.removeAllItems();

        List<TypeOfWork> typeOfWorks = typeOfWorkService.getAllTypeOfWorks();
        List<Mechanic> mechanics = mechanicService.getAllMechanics();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<Client> clients = clientService.getAllClients();

        workDialog.typeOfWorkDialogCBox.addItem("Selecciona un servicio");
        workDialog.mechanicWorkDialogCBox.addItem("Selecciona un mecanico");
        workDialog.vehicleWorkDialogCBox.addItem("Selecciona un vehiculo");
        workDialog.clientWorkDialogCBox.addItem("Selecciona un cliente");

        typeOfWorks.forEach( item -> workDialog.typeOfWorkDialogCBox.addItem(item.getFolio().toString()) );
        mechanics.forEach( item -> workDialog.mechanicWorkDialogCBox.addItem(item.getFolio().toString()) );
        vehicles.forEach( item -> workDialog.vehicleWorkDialogCBox.addItem(item.getPlaque()) );
        clients.forEach( item -> workDialog.clientWorkDialogCBox.addItem(item.getFolio().toString()) );
    }

    private void refreshTable() {

        DefaultTableModel model = (DefaultTableModel) this.workDialog.tableWorkDialog.getModel();

        deleteAllItemsOfTable(workDialog.tableWorkDialog.getRowCount(), model, 0);

        workService.getAllWorks().forEach( item -> {
            String[] content = {
                    item.getFolio().toString(),
                    item.getTypeOfWork().getFolio().toString(),
                    item.getMechanic().getFolio().toString(),
                    item.getVehicle().getPlaque(),
                    item.getCost().toString(),
                    item.getClient().getFolio().toString(),
                    item.getFechaRecibido().toString(),
                    item.getFechaEntrega().toString(),
                    item.getIssues()
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
         * update work dialog button
         */
        if ( e.getSource() == workDialog.updateWorkDialogButton ) {

            WorkValidations workValidations = new WorkValidationsImpl();

            if ( !workValidations.validateTypeServiceWork(workDialog.typeOfWorkDialogCBox, workDialog.folioWorkDialogValidation) ||
                !workValidations.validateMechanicWork(workDialog.mechanicWorkDialogCBox, workDialog.mechanicWorkDialogValidation) ||
                !workValidations.validateVehicleWork(workDialog.vehicleWorkDialogCBox, workDialog.vehicleWorkDialogValidation) ||
                !workValidations.validatePriceWork(workDialog.priceWorkDialogField, workDialog.priceWorkDialogValidation) ||
                !workValidations.validateClientWork(workDialog.clientWorkDialogCBox, workDialog.clientWorkDialogValidation) ||
                !workValidations.validateDateReceiveWork(workDialog.dateReceiveWorkDialogDChooser, workDialog.dateReceibeWorkDialogValidation) ||
                !workValidations.validateDateSendWork(workDialog.dateSendWorkDialogDChooser, workDialog.dateSendWorkDialogValidation) ||
                !workValidations.validateIssuesWork(workDialog.issuesWorkDialogTArea, workDialog.issuesWorkDialogValidation) ||
                workDialog.folioWorkDialogField.getText().trim().equals("") ) {

                JOptionPane.showMessageDialog(null, "Seleccione un servicio",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else {

                Long selectedFolio = Long.parseLong(workDialog.folioWorkDialogField.getText());
                TypeOfWork selecteTypeOfWork = typeOfWorkService.getTypeOfWorkByFolio(Long.parseLong(workDialog.typeOfWorkDialogCBox.getSelectedItem().toString()));
                Mechanic selectedMechanic = mechanicService.getMechanicByFolio(Long.parseLong(workDialog.mechanicWorkDialogCBox.getSelectedItem().toString()));
                Vehicle selectedVehicle = vehicleService.getVehicleByPlaque(workDialog.vehicleWorkDialogCBox.getSelectedItem().toString());
                Double selectedCost = Double.parseDouble(workDialog.priceWorkDialogField.getText());
                Date selectedFechaRecibido = workDialog.dateReceiveWorkDialogDChooser.getDate();
                Date selectedFechaEntrega = workDialog.dateSendWorkDialogDChooser.getDate();
                Client selectedClient = clientService.getClientByFolio(Long.parseLong(workDialog.clientWorkDialogCBox.getSelectedItem().toString()));
                String selectedIssues = workDialog.issuesWorkDialogTArea.getText();

                Work work = new Work(selectedFolio, selecteTypeOfWork, selectedMechanic
                        , selectedVehicle, selectedCost, selectedFechaRecibido
                        , selectedFechaEntrega, selectedClient, selectedIssues);

                workService.updateWork(work);

                workDialog.folioWorkDialogField.setText("");
                workDialog.priceWorkDialogField.setText("");
                workDialog.issuesWorkDialogTArea.setText("");
                ((JTextField) workDialog.dateReceiveWorkDialogDChooser.getDateEditor().getUiComponent()).setText("");
                ((JTextField) workDialog.dateSendWorkDialogDChooser.getDateEditor().getUiComponent()).setText("");

                refreshTable();
                refreshList();

                JOptionPane.showMessageDialog(null, "Informacion actualizada",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        /**
         * delete work dialog action
         */
        if ( e.getSource() == workDialog.deleteWorkDialogButton ) {
            
            WorkValidations workValidations = new WorkValidationsImpl();

            if ( !workValidations.validateTypeServiceWork(workDialog.typeOfWorkDialogCBox, workDialog.folioWorkDialogValidation) ||
                    !workValidations.validateMechanicWork(workDialog.mechanicWorkDialogCBox, workDialog.mechanicWorkDialogValidation) ||
                    !workValidations.validateVehicleWork(workDialog.vehicleWorkDialogCBox, workDialog.vehicleWorkDialogValidation) ||
                    !workValidations.validatePriceWork(workDialog.priceWorkDialogField, workDialog.priceWorkDialogValidation) ||
                    !workValidations.validateClientWork(workDialog.clientWorkDialogCBox, workDialog.clientWorkDialogValidation) ||
                    !workValidations.validateDateReceiveWork(workDialog.dateReceiveWorkDialogDChooser, workDialog.dateReceibeWorkDialogValidation) ||
                    !workValidations.validateDateSendWork(workDialog.dateSendWorkDialogDChooser, workDialog.dateSendWorkDialogValidation) ||
                    !workValidations.validateIssuesWork(workDialog.issuesWorkDialogTArea, workDialog.issuesWorkDialogValidation) ||
                    workDialog.folioWorkDialogField.getText().trim().equals("") ) {

                JOptionPane.showMessageDialog(null, "Seleccione un servicio",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else {

                long selectedFolio = Long.parseLong(workDialog.folioWorkDialogField.getText());

                workService.removeWorkByFolio(selectedFolio);

                workDialog.folioWorkDialogField.setText("");
                workDialog.priceWorkDialogField.setText("");
                workDialog.issuesWorkDialogTArea.setText("");
                ((JTextField) workDialog.dateReceiveWorkDialogDChooser.getDateEditor().getUiComponent()).setText("");
                ((JTextField) workDialog.dateSendWorkDialogDChooser.getDateEditor().getUiComponent()).setText("");

                refreshTable();
                refreshList();

                JOptionPane.showMessageDialog(null, "Registro eliminado",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }
}
