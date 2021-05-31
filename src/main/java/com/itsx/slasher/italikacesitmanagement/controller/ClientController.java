package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.model.Client;
import com.itsx.slasher.italikacesitmanagement.service.ClientService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.validations.ClientValidations;
import com.itsx.slasher.italikacesitmanagement.validations.impl.ClientValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.view.ClientDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ClientController implements ActionListener {

    private ClientService clientService;
    private ClientDialog clientDialog;
    private WorkService workService;

    public ClientController(ClientDialog clientDialog, ClientService clientService, WorkService workService) {
        this.clientDialog = clientDialog;
        this.clientService = clientService;
        this.workService = workService;

        this.clientDialog.updateClientDialogButton.addActionListener(this);
        this.clientDialog.deleteClientDialogButton.addActionListener(this);
    }

    public void start() {
        this.clientDialog.setVisible(true);
        runValidations();
        refreshTable();
    }

    public void runValidations() {

        this.clientDialog.nameClientDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ClientValidations clientValidations = new ClientValidationsImpl();

                clientValidations.validateClientName(clientDialog.nameClientDialogField
                        , clientDialog.nameClientDialogValidation);
            }
        });

        this.clientDialog.lastnameClientDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ClientValidations clientValidations = new ClientValidationsImpl();

                clientValidations.validateClientLastName(clientDialog.lastnameClientDialogField
                        , clientDialog.lastnameClientDialogValidation);
            }
        });

        this.clientDialog.motherLastnameClientDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ClientValidations clientValidations = new ClientValidationsImpl();

                clientValidations.validateClientMotherLastName(clientDialog.motherLastnameClientDialogField
                        , clientDialog.motherLastnameClientDialogValidation);
            }
        });


        this.clientDialog.cellphoneClientDialogField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ClientValidations clientValidations = new ClientValidationsImpl();

                clientValidations.validateClientCellphone(clientDialog.cellphoneClientDialogField
                        , clientDialog.cellphoneClientDialogValidation);
            }
        });

    }

    public void refreshTable() {

        DefaultTableModel model = (DefaultTableModel) clientDialog.tableClientDialog.getModel();

        deleteAllItemsOfTable(model.getRowCount(), model, 0);

        clientService.getAllClients().forEach( item -> {
            String[] content = {
                    item.getFolio().toString(),
                    item.getName(),
                    item.getLastName(),
                    item.getMotherLastName(),
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

        if ( e.getSource() == clientDialog.updateClientDialogButton ) {

            ClientValidations clientValidations = new ClientValidationsImpl();

            if ( !clientValidations.validateClientName(clientDialog.nameClientDialogField, clientDialog.nameClientDialogValidation) ||
                !clientValidations.validateClientLastName(clientDialog.lastnameClientDialogField, clientDialog.lastnameClientDialogValidation) ||
                !clientValidations.validateClientMotherLastName(clientDialog.motherLastnameClientDialogField, clientDialog.motherLastnameClientDialogValidation) ||
                !clientValidations.validateClientCellphone(clientDialog.cellphoneClientDialogField, clientDialog.cellphoneClientDialogValidation) ||
                clientDialog.folioClientDialogField.getText().trim().equals("") ) {

                JOptionPane.showMessageDialog(null, "Seleccione un cliente",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else {

                Long selectedFolio = Long.parseLong( clientDialog.folioClientDialogField.getText() );
                String selectedName = clientDialog.nameClientDialogField.getText();
                String selectedLastname = clientDialog.lastnameClientDialogField.getText();
                String selectedMotherLastname = clientDialog.motherLastnameClientDialogField.getText();
                String selectedCellphone = clientDialog.cellphoneClientDialogField.getText();

                Client client = new Client(selectedFolio, selectedName, selectedLastname, selectedMotherLastname, selectedCellphone);

                clientService.updateClient(client);

                clientDialog.folioClientDialogField.setText("");
                clientDialog.nameClientDialogField.setText("");
                clientDialog.lastnameClientDialogField.setText("");
                clientDialog.motherLastnameClientDialogField.setText("");
                clientDialog.cellphoneClientDialogField.setText("");

                refreshTable();

                JOptionPane.showMessageDialog(null, "Informacion actualizada",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        /**
         * delete client dialog action
         */
        if ( e.getSource() == clientDialog.deleteClientDialogButton ) {

            ClientValidations clientValidations = new ClientValidationsImpl();

            if ( !clientValidations.validateClientName(clientDialog.nameClientDialogField, clientDialog.nameClientDialogValidation) ||
                !clientValidations.validateClientLastName(clientDialog.lastnameClientDialogField, clientDialog.lastnameClientDialogValidation) ||
                !clientValidations.validateClientMotherLastName(clientDialog.motherLastnameClientDialogField, clientDialog.motherLastnameClientDialogValidation) ||
                !clientValidations.validateClientCellphone(clientDialog.cellphoneClientDialogField, clientDialog.cellphoneClientDialogValidation) ||
                clientDialog.folioClientDialogField.getText().trim().equals("") ) {

                JOptionPane.showMessageDialog(null, "Seleccione un cliente",
                        "Atencion", JOptionPane.ERROR_MESSAGE);

            } else if ( clientValidations.validateReferencesInOtherEntities(Long
                    .parseLong(clientDialog.folioClientDialogField.getText()), workService) ) {

                JOptionPane.showMessageDialog(null, "No se puede eliminar este elemento porque hay otra tabla que la esta referenciando",
                        "Error", JOptionPane.ERROR_MESSAGE);

                JOptionPane.showMessageDialog(null, "Elimine los registros que hacen referencia a este item antes de eliminarlo",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            } else {

                long selectedFolio = Long.parseLong(clientDialog.folioClientDialogField.getText());

                clientService.removeClientByFolio(selectedFolio);

                clientDialog.folioClientDialogField.setText("");
                clientDialog.nameClientDialogField.setText("");
                clientDialog.lastnameClientDialogField.setText("");
                clientDialog.motherLastnameClientDialogField.setText("");
                clientDialog.cellphoneClientDialogValidation.setText("");

                refreshTable();

                JOptionPane.showMessageDialog(null, "Registro eliminado",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }

}
