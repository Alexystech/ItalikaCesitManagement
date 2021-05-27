/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.model.Mechanic;
import com.itsx.slasher.italikacesitmanagement.model.TypeOfWork;
import com.itsx.slasher.italikacesitmanagement.model.Vehicle;
import com.itsx.slasher.italikacesitmanagement.service.ClientService;
import com.itsx.slasher.italikacesitmanagement.service.MechanicService;
import com.itsx.slasher.italikacesitmanagement.service.TypeOfWorkService;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.service.impl.TypeOfWorkServiceImpl;
import com.itsx.slasher.italikacesitmanagement.validations.ClientValidations;
import com.itsx.slasher.italikacesitmanagement.validations.MechanicValidations;
import com.itsx.slasher.italikacesitmanagement.validations.TypeOfWorkValidations;
import com.itsx.slasher.italikacesitmanagement.validations.VehicleValidations;
import com.itsx.slasher.italikacesitmanagement.validations.WorkValidations;
import com.itsx.slasher.italikacesitmanagement.validations.impl.ClientValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.validations.impl.MechanicValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.validations.impl.TypeOfWorkValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.validations.impl.VehicleValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.validations.impl.WorkValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.view.DashboardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;

/**
 *
 * @author defin
 */
public class DashboardController implements ActionListener {
    
    private DashboardLayout dashboardLayout;
    private ClientService clientService;
    private MechanicService mechanicService;
    private TypeOfWorkService typeOfWorkService;
    private VehicleService vehicleService;
    private WorkService workService;
    
    public DashboardController(DashboardLayout dashboardLayout, ClientService clientService
            , MechanicService mechanicService, TypeOfWorkService typeOfWorkService
            , VehicleService vehicleService, WorkService workService) {
        this.dashboardLayout = dashboardLayout;
        this.clientService = clientService;
        this.mechanicService = mechanicService;
        this.typeOfWorkService = typeOfWorkService;
        this.vehicleService = vehicleService;
        this.workService = workService;
        this.dashboardLayout.agregarClienteButton.addActionListener(this);
        this.dashboardLayout.agregarMecanicoButton.addActionListener(this);
        this.dashboardLayout.agregarServicioButton.addActionListener(this);
        this.dashboardLayout.agregarTipoDeTrabajoButton.addActionListener(this);
        this.dashboardLayout.agregarVehiculoButton.addActionListener(this);
        
        this.dashboardLayout.serviceManagerLayoutButton.addActionListener(this);
        this.dashboardLayout.clientLayoutButton.addActionListener(this);
        this.dashboardLayout.mechanicsLayoutButton.addActionListener(this);
        this.dashboardLayout.vehiclesLayoutButton.addActionListener(this);
        this.dashboardLayout.typeWorkLayoutButton.addActionListener(this);
        this.dashboardLayout.logoutLayoutButton.addActionListener(this);
    }
    
    public void start() {
        dashboardLayout.setTitle("ITALIKA CESIT DashboardManagement");
        dashboardLayout.setLocationRelativeTo(null);
        dashboardLayout.setVisible(true);
        dashboardLayout.setSize(1080,720);
        dashboardLayout.setLocationRelativeTo(null);
        dashboardLayout.setResizable(false);
        dashboardLayout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void runValidations() {
        dashboardLayout.nameClientField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ClientValidations clientValidations = new ClientValidationsImpl();
                clientValidations.validateClientName(dashboardLayout.nameClientField
                        , dashboardLayout.nameClientValidation);
            }
        });

        dashboardLayout.lastNameClientField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ClientValidations clientValidations = new ClientValidationsImpl();
                clientValidations.validateClientLastName(dashboardLayout.lastNameClientField
                        , dashboardLayout.lastNameClientValidation);
            }
        });

        dashboardLayout.motherLastNameClientField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ClientValidations clientValidations = new ClientValidationsImpl();
                clientValidations.validateClientMotherLastName(dashboardLayout.motherLastNameClientField
                        , dashboardLayout.motherLastNameClientValidation);
            }
        });

        dashboardLayout.cellphoneClientField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ClientValidations clientValidations = new ClientValidationsImpl();
                clientValidations.validateClientCellphone(dashboardLayout.cellphoneClientField
                        , dashboardLayout.cellphoneClientValidation);
            }
        });

        dashboardLayout.nameMechanicField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();
                mechanicValidations.validateMechanicName(dashboardLayout.nameMechanicField
                        , dashboardLayout.nameMechanicValidation);
            }
        });

        dashboardLayout.lastNameMechanicField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();
                mechanicValidations.validateMechanicLastName(dashboardLayout.lastNameMechanicField
                        , dashboardLayout.lastNameMechanicValidation);
            }
        });

        dashboardLayout.motherLastNameMechanicField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();
                mechanicValidations.validateMechanicMotherLastName(dashboardLayout.motherLastNameMechanicField
                        , dashboardLayout.motherLastNameMechanicValidation);
            }
        });

        dashboardLayout.specialityMechanicField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();
                mechanicValidations.validateMechanicSpeciality(dashboardLayout.specialityMechanicField
                        , dashboardLayout.specialityMechanicValidation);
            }
        });

        dashboardLayout.cellphoneMechanicField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MechanicValidations mechanicValidations = new MechanicValidationsImpl();
                mechanicValidations.validateMechanicCellphone(dashboardLayout.cellphoneMechanicField
                        , dashboardLayout.cellphoneMechanicValidation);
            }
        });

        dashboardLayout.plaqueVehicleField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                VehicleValidations vehicleValidations = new VehicleValidationsImpl();
                vehicleValidations.validatePlaqueVehicle(dashboardLayout.plaqueVehicleField
                        , dashboardLayout.plaqueVehicleValidation);
            }
        });

        dashboardLayout.brandVehicleField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                VehicleValidations vehicleValidations = new VehicleValidationsImpl();
                vehicleValidations.validateBrandVehicle(dashboardLayout.brandVehicleField
                        , dashboardLayout.brandVehicleValidation);
            }
        });

        dashboardLayout.modelVehicleField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                VehicleValidations vehicleValidations = new VehicleValidationsImpl();
                vehicleValidations.validateModelVehicle(dashboardLayout.modelVehicleField
                        , dashboardLayout.modelVehicleValidation);
            }
        });

        dashboardLayout.yearVehicleCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                VehicleValidations vehicleValidations = new VehicleValidationsImpl();
                vehicleValidations.validateYearVehicle(dashboardLayout.yearVehicleCBox
                        , dashboardLayout.yearVehicleValidation);
            }
        });

        dashboardLayout.nameTWorkField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                TypeOfWorkValidations typeOfWorkValidations = new TypeOfWorkValidationsImpl();
                typeOfWorkValidations.validateNameTypeOfWork(dashboardLayout.nameTWorkField
                        , dashboardLayout.nameTWorkValidation);
            }
        });

        dashboardLayout.typeServiceCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();
                workValidations.validateTypeServiceWork(dashboardLayout.typeServiceCBox
                        , dashboardLayout.typeServiceWorkValidation);
            }
        });

        dashboardLayout.mechanicCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();
                workValidations.validateMechanicWork(dashboardLayout.mechanicCBox
                        , dashboardLayout.mechanicWorkValidation);
            }
        });

        dashboardLayout.vehicleCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();
                workValidations.validateVehicleWork(dashboardLayout.vehicleCBox
                        , dashboardLayout.vehicleWorkValidation);
            }
        });

        dashboardLayout.priceServiceField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();
                workValidations.validatePriceWork(dashboardLayout.priceServiceField
                        , dashboardLayout.priceWorkValidation);
            }
        });

        dashboardLayout.dateReceive.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();
                workValidations.validateDateReceiveWork(dashboardLayout.dateReceive
                        , dashboardLayout.dateReceiveWorkValidation);
            }
        });

        dashboardLayout.dateSend.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();
                workValidations.validateDateSendWork(dashboardLayout.dateSend
                        , dashboardLayout.dateSendWorkValidation);
            }
        });

        dashboardLayout.clientCBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();
                workValidations.validateClientWork(dashboardLayout.clientCBox
                        , dashboardLayout.clientWorkValidation);
            }
        });

        dashboardLayout.issuesTPane.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                WorkValidations workValidations = new WorkValidationsImpl();
                workValidations.validateIssuesWork(dashboardLayout.issuesTPane
                        , dashboardLayout.issuesWorkValidation);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if ( arg0.getSource() == dashboardLayout.logoutLayoutButton ) {

        }

        if ( arg0.getSource() == dashboardLayout.agregarTipoDeTrabajoButton ) {

            TypeOfWorkValidations typeOfWorkValidations = new TypeOfWorkValidationsImpl();

            if ( !typeOfWorkValidations.validateNameTypeOfWork(dashboardLayout.nameTWorkField
                    , dashboardLayout.nameTWorkValidation) ) {

                JOptionPane.showMessageDialog(null, "llene correctamente los campos",
                        "Atencion", JOptionPane.WARNING_MESSAGE);

            } else {

                String nameTypeOfWork = dashboardLayout.nameTWorkField.getText();

                TypeOfWork typeOfWork = new TypeOfWork(nameTypeOfWork);
                TypeOfWorkService typeOfWorkService = new TypeOfWorkServiceImpl();

                typeOfWorkService.createTypeOfWork(typeOfWork);

                dashboardLayout.nameTWorkField.setText("");

                JOptionPane.showMessageDialog(null,"Tipo de trabajo generado con éxito!",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        if ( arg0.getSource() == dashboardLayout.agregarVehiculoButton ) {

            VehicleValidations vehicleValidations = new VehicleValidationsImpl();

            if ( !vehicleValidations.validatePlaqueVehicle(dashboardLayout.plaqueVehicleField, dashboardLayout.plaqueVehicleValidation) ||
                !vehicleValidations.validateBrandVehicle(dashboardLayout.brandVehicleField, dashboardLayout.brandVehicleValidation)  ||
                !vehicleValidations.validateModelVehicle(dashboardLayout.modelVehicleField, dashboardLayout.modelVehicleValidation)  ||
                !vehicleValidations.validateYearVehicle(dashboardLayout.yearVehicleCBox, dashboardLayout.yearVehicleValidation)) {

                JOptionPane.showMessageDialog(null, "llene correctamente los campos"
                        , "Atencion", JOptionPane.WARNING_MESSAGE);

            } else {

                String plaque = dashboardLayout.plaqueVehicleField.getText();
                String brand = dashboardLayout.brandVehicleField.getText();
                String model = dashboardLayout.modelVehicleField.getText();
                int year = Integer.parseInt(dashboardLayout.yearVehicleCBox.getSelectedItem().toString());

                Vehicle vehicle = new Vehicle(plaque, brand, model, year);

                vehicleService.createVehicle(vehicle);

                dashboardLayout.plaqueVehicleField.setText("");
                dashboardLayout.brandVehicleField.setText("");
                dashboardLayout.modelVehicleField.setText("");
                dashboardLayout.yearVehicleCBox.setSelectedIndex(0);

                JOptionPane.showMessageDialog(null, "Vehiculo generado con éxito!"
                        , "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        if ( arg0.getSource() == dashboardLayout.agregarMecanicoButton ) {

            MechanicValidations mechanicValidations = new MechanicValidationsImpl();

            if ( !mechanicValidations.validateMechanicName(dashboardLayout.nameMechanicField, dashboardLayout.nameMechanicValidation) ||
                !mechanicValidations.validateMechanicLastName(dashboardLayout.lastNameMechanicField, dashboardLayout.lastNameMechanicValidation) ||
                !mechanicValidations.validateMechanicMotherLastName(dashboardLayout.motherLastNameMechanicField, dashboardLayout.motherLastNameMechanicValidation) ||
                !mechanicValidations.validateMechanicSpeciality(dashboardLayout.specialityMechanicField, dashboardLayout.specialityMechanicValidation) ||
                !mechanicValidations.validateMechanicCellphone(dashboardLayout.cellphoneMechanicField, dashboardLayout.cellphoneMechanicValidation)) {

                JOptionPane.showMessageDialog(null, "llene correctamente los campos",
                        "Atencion", JOptionPane.WARNING_MESSAGE);

            } else {

                String name = dashboardLayout.nameMechanicField.getText();
                String lastName = dashboardLayout.lastNameMechanicField.getText();
                String motherLastName = dashboardLayout.motherLastNameMechanicField.getText();
                String speciality = dashboardLayout.specialityMechanicField.getText();
                String cellphone = dashboardLayout.cellphoneMechanicField.getText();

                Mechanic mechanic = new Mechanic(name, lastName, motherLastName, speciality, cellphone);

                mechanicService.createMechanic(mechanic);

                dashboardLayout.nameMechanicField.setText("");
                dashboardLayout.lastNameMechanicField.setText("");
                dashboardLayout.motherLastNameMechanicField.setText("");
                dashboardLayout.specialityMechanicField.setText("");
                dashboardLayout.cellphoneMechanicField.setText("");

                JOptionPane.showMessageDialog(null, "Mecanico generado con éxito!",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }
    
}
