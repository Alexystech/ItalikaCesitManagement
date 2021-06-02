/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.model.Client;
import com.itsx.slasher.italikacesitmanagement.model.Mechanic;
import com.itsx.slasher.italikacesitmanagement.model.TypeOfWork;
import com.itsx.slasher.italikacesitmanagement.model.Vehicle;
import com.itsx.slasher.italikacesitmanagement.model.Work;
import com.itsx.slasher.italikacesitmanagement.service.AdministratorService;
import com.itsx.slasher.italikacesitmanagement.service.ClientService;
import com.itsx.slasher.italikacesitmanagement.service.MechanicService;
import com.itsx.slasher.italikacesitmanagement.service.TypeOfWorkService;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.service.impl.AdministratorServiceImpl;
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
import com.itsx.slasher.italikacesitmanagement.view.ClientDialog;
import com.itsx.slasher.italikacesitmanagement.view.DashboardLayout;
import com.itsx.slasher.italikacesitmanagement.view.LoginLayout;
import com.itsx.slasher.italikacesitmanagement.view.MechanicDialog;
import com.itsx.slasher.italikacesitmanagement.view.TypeOfWorksDialog;
import com.itsx.slasher.italikacesitmanagement.view.VehicleDialog;
import com.itsx.slasher.italikacesitmanagement.view.WorkDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;

/**
 *
 * @author defin
 */
public class DashboardController implements ActionListener, Runnable {
    
    private DashboardLayout dashboardLayout;
    private ClientService clientService;
    private MechanicService mechanicService;
    private TypeOfWorkService typeOfWorkService;
    private VehicleService vehicleService;
    private WorkService workService;

    private JFreeChart barChart;
    private JFreeChart pieChartMechanic;
    private JFreeChart pieChartClient;
    private ChartPanel panelBarChart;
    private ChartPanel panelPieChartMechanic;
    private ChartPanel panelPieChartClient;
    
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
        this.dashboardLayout.typeWorkDialogButton.addActionListener(this);
        this.dashboardLayout.logoutLayoutButton.addActionListener(this);
    }
    
    public void startDashboard() {
        dashboardLayout.setTitle("ITALIKA CESIT DashboardManagement");
        dashboardLayout.setVisible(true);
        dashboardLayout.setSize(1080,720);
        dashboardLayout.setLocationRelativeTo(null);
        dashboardLayout.setResizable(true);
        dashboardLayout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        barChart = ChartFactory.createBarChart(
                "Cantidad de servicios por modelo",
                "Modelos",
                "Servicios",
                null,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );

        pieChartMechanic = ChartFactory.createPieChart(
                "Cantidad de trabajos por mecanico",
                null,
                true,
                true,
                false
        );

        pieChartClient = ChartFactory.createPieChart(
                "Cantidad de trabajos por cliente",
                null,
                true,
                true,
                false
        );

        panelBarChart = new ChartPanel(barChart);
        panelPieChartMechanic = new ChartPanel(pieChartMechanic);
        panelPieChartClient = new ChartPanel(pieChartClient);

        panelBarChart.setMouseWheelEnabled(true);
        panelBarChart.setPreferredSize(new Dimension(556,500));

        panelPieChartMechanic.setMouseWheelEnabled(true);
        panelPieChartMechanic.setPreferredSize(new Dimension(400,250));

        panelPieChartClient.setMouseWheelEnabled(true);
        panelPieChartClient.setPreferredSize(new Dimension(400,250));

        this.dashboardLayout.barChart.setLayout(new BorderLayout());
        this.dashboardLayout.barChart.add(panelBarChart, BorderLayout.NORTH);

        this.dashboardLayout.paiChart1.setLayout(new BorderLayout());
        this.dashboardLayout.paiChart1.add(panelPieChartMechanic, BorderLayout.NORTH);

        this.dashboardLayout.paiChart2.setLayout(new BorderLayout());
        this.dashboardLayout.paiChart2.add(panelPieChartClient, BorderLayout.NORTH);

        refreshList();
    }

    private void refreshList() {

        dashboardLayout.typeServiceCBox.removeAllItems();
        dashboardLayout.mechanicCBox.removeAllItems();
        dashboardLayout.vehicleCBox.removeAllItems();
        dashboardLayout.clientCBox.removeAllItems();

        List<TypeOfWork> typeOfWorks = typeOfWorkService.getAllTypeOfWorks();
        List<Mechanic> mechanics = mechanicService.getAllMechanics();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<Client> clients = clientService.getAllClients();

        dashboardLayout.typeServiceCBox.addItem("Selecciona un servicio");
        dashboardLayout.mechanicCBox.addItem("Selecciona un mecanico");
        dashboardLayout.vehicleCBox.addItem("Selecciona un vehiculo");
        dashboardLayout.clientCBox.addItem("Selecciona un cliente");

        typeOfWorks.forEach( item -> dashboardLayout.typeServiceCBox.addItem(item.getFolio().toString()) );
        mechanics.forEach( item -> dashboardLayout.mechanicCBox.addItem(item.getFolio().toString()) );
        vehicles.forEach( item -> dashboardLayout.vehicleCBox.addItem(item.getPlaque()) );
        clients.forEach( item -> dashboardLayout.clientCBox.addItem(item.getFolio().toString()) );

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
    public void run() {

        while (this.dashboardLayout.isVisible()) {

            /**
             * data barchart
             */
            List<Work> works = this.workService.getAllWorks();
            List<String> models = this.vehicleService.getAllVehicles().stream()
                    .map(item -> item.getModel())
                    .collect(Collectors.toList());

            Map<String, Integer> dataBarchart = dataBarchart(models, works);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataBarchart.forEach( (key, data) -> {
                dataset.addValue(data, "Servicios", key);
            });

            /**
             * data pie chart mechanic
             */
            List<Long> folioMechanics = this.mechanicService.getAllMechanics().stream()
                    .map(Mechanic::getFolio)
                    .collect(Collectors.toList());

            Map<String, Integer> dataPieChartMechanic = dataPieChartMechanic(folioMechanics, works);
            DefaultPieDataset pieDatasetMechanic = new DefaultPieDataset();

            dataPieChartMechanic.forEach( (key, data) -> {
                pieDatasetMechanic.setValue(key, data);
            });

            /**
             * data pie chart client
             */
            List<Long> folioClients = this.clientService.getAllClients().stream()
                    .map(Client::getFolio)
                    .collect(Collectors.toList());

            Map<String, Integer> dataPieChartClient = dataPieChartClient(folioClients, works);
            DefaultPieDataset pieDatasetClient = new DefaultPieDataset();

            dataPieChartClient.forEach( (key, data) -> {
                pieDatasetClient.setValue(key, data);
            });

            /**
             * plot charts
             */
            barChart = ChartFactory.createBarChart(
                    "Cantidad de servicios por modelo",
                    "Modelos",
                    "Servicios",
                    dataset,
                    PlotOrientation.HORIZONTAL,
                    true,
                    true,
                    false
            );

            panelBarChart.setChart(barChart);
            this.dashboardLayout.barChart.add(panelBarChart, BorderLayout.NORTH);

            pieChartMechanic = ChartFactory.createPieChart(
                    "Cantidad de trabajos por mecanico",
                    pieDatasetMechanic,
                    true,
                    true,
                    false
            );

            panelPieChartMechanic.setChart(pieChartMechanic);
            this.dashboardLayout.paiChart1.repaint();

            pieChartClient = ChartFactory.createPieChart(
                    "Cantidad de trabajos por cliente",
                    pieDatasetClient,
                    true,
                    true,
                    false
            );

            panelPieChartClient.setChart(pieChartClient);
            this.dashboardLayout.paiChart2.repaint();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private Map<String, Integer> dataBarchart(List<String> modelVehicles, List<Work> works) {

        Map<String, Integer> data = new HashMap<>();

        for ( String model : modelVehicles ) {

            data.put(model, (int) works.stream()
                    .filter(work -> work.getVehicle().getModel().equals(model))
                    .count()
            );

        }

        return data;
    }

    private Map<String, Integer> dataPieChartMechanic(List<Long> folioMechanics, List<Work> works) {

        Map<String, Integer> data = new HashMap<>();

        for ( Long folio : folioMechanics ) {

            Mechanic mechanic = mechanicService.getMechanicByFolio(folio);
            String nameMechanic = "F" + folio + " - " + mechanic.getName() + " " + mechanic.getLastName();

            data.put(nameMechanic, (int) works.stream()
                    .filter(work -> work.getMechanic().getFolio().equals(folio))
                    .count()
            );

        }

        return data;
    }

    private Map<String, Integer> dataPieChartClient(List<Long> folioClients, List<Work> works) {

        Map<String, Integer> data = new HashMap<>();

        for ( Long folio : folioClients ) {

            Client client = clientService.getClientByFolio(folio);
            String nameClient = "F" + folio + " - " + client.getName() + " " + client.getLastName();

            data.put(nameClient, (int) works.stream()
                    .filter(work -> work.getClient().getFolio().equals(folio))
                    .count()
            );

        }

        return data;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        /**
         * logout button action performed
         */
        if ( arg0.getSource() == dashboardLayout.logoutLayoutButton ) {

            dashboardLayout.setVisible(false);

            LoginLayout loginLayout = new LoginLayout();
            AdministratorService administratorService = new AdministratorServiceImpl();

            LoginController loginController = new LoginController(loginLayout, administratorService);
            loginController.start();
            loginController.runValidations();

        }

        /**
         * Actions update/delete type of work dialog button
         */
        if ( arg0.getSource() == dashboardLayout.typeWorkDialogButton ) {

            TypeOfWorksDialog typeOfWorksDialog = new TypeOfWorksDialog(typeOfWorkService, dashboardLayout, true);
            TypeOfWorkController typeOfWorkController = new TypeOfWorkController(typeOfWorksDialog, typeOfWorkService, workService);

            typeOfWorkController.start();
        }

        /**
         * Actions update/delete vehicle dialog button
         */
        if ( arg0.getSource() == dashboardLayout.vehiclesLayoutButton ) {

            VehicleDialog vehicleDialog = new VehicleDialog(vehicleService, dashboardLayout, true);
            VehicleController vehicleController = new VehicleController(vehicleDialog, vehicleService, workService);

            vehicleController.start();

        }

        /**
         * Actions update/delete mechanic dialog button
         */
        if ( arg0.getSource() == dashboardLayout.mechanicsLayoutButton ) {

            MechanicDialog mechanicDialog = new MechanicDialog(mechanicService, dashboardLayout, true);
            MechanicController mechanicController = new MechanicController(mechanicDialog, mechanicService, workService);

            mechanicController.start();

        }

        /**
         * actions update/delete client dialog button
         */
        if ( arg0.getSource() == dashboardLayout.clientLayoutButton ) {

            ClientDialog clientDialog = new ClientDialog(clientService, dashboardLayout, true);
            ClientController clientController = new ClientController(clientDialog, clientService, workService);

            clientController.start();

        }

        /**
         * actions update/delete work dialog button
         */
        if ( arg0.getSource() == dashboardLayout.serviceManagerLayoutButton ) {

            WorkDialog workDialog = new WorkDialog(workService, dashboardLayout, true);
            WorkController workController = new WorkController(workService, workDialog, typeOfWorkService
                    , vehicleService, mechanicService, clientService);

            workController.start();

        }

        /**
         * Add type of work action button
         */
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

                refreshList();
            }

        }

        /**
         * add vehicle action button
         */
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

                refreshList();

            }

        }

        /**
         * add mechanic action button
         */
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

                refreshList();

            }

        }

        /**
         * add client action button
         */
        if ( arg0.getSource() == dashboardLayout.agregarClienteButton ) {

            ClientValidations clientValidations = new ClientValidationsImpl();

            if ( !clientValidations.validateClientName(dashboardLayout.nameClientField, dashboardLayout.nameClientValidation) ||
                !clientValidations.validateClientLastName(dashboardLayout.lastNameClientField, dashboardLayout.lastNameClientValidation) ||
                !clientValidations.validateClientMotherLastName(dashboardLayout.motherLastNameClientField, dashboardLayout.motherLastNameClientValidation) ||
                !clientValidations.validateClientCellphone(dashboardLayout.cellphoneClientField, dashboardLayout.cellphoneClientValidation)) {

                JOptionPane.showMessageDialog(null, "llene correctamente los campos",
                        "atencion", JOptionPane.WARNING_MESSAGE);

            } else {

                String name = dashboardLayout.nameClientField.getText();
                String lastName = dashboardLayout.lastNameClientField.getText();
                String motherLastName = dashboardLayout.motherLastNameClientField.getText();
                String cellphone = dashboardLayout.cellphoneClientField.getText();

                Client client = new Client(name, lastName, motherLastName, cellphone);

                clientService.createClient(client);

                dashboardLayout.nameClientField.setText("");
                dashboardLayout.lastNameClientField.setText("");
                dashboardLayout.motherLastNameClientField.setText("");
                dashboardLayout.cellphoneClientField.setText("");

                JOptionPane.showMessageDialog(null,"cliente generado con éxito",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

                refreshList();

            }

        }

        /**
         * add service action button
         */
        if ( arg0.getSource() == dashboardLayout.agregarServicioButton ) {

            WorkValidations workValidations = new WorkValidationsImpl();

            if ( !workValidations.validateTypeServiceWork(dashboardLayout.typeServiceCBox, dashboardLayout.typeServiceWorkValidation) ||
                !workValidations.validateMechanicWork(dashboardLayout.mechanicCBox, dashboardLayout.mechanicWorkValidation) ||
                !workValidations.validateVehicleWork(dashboardLayout.vehicleCBox, dashboardLayout.vehicleWorkValidation) ||
                !workValidations.validatePriceWork(dashboardLayout.priceServiceField, dashboardLayout.priceWorkValidation) ||
                !workValidations.validateDateReceiveWork(dashboardLayout.dateReceive, dashboardLayout.dateReceiveWorkValidation) ||
                !workValidations.validateDateSendWork(dashboardLayout.dateSend, dashboardLayout.dateSendWorkValidation) ||
                !workValidations.validateClientWork(dashboardLayout.clientCBox, dashboardLayout.clientWorkValidation) ||
                !workValidations.validateIssuesWork(dashboardLayout.issuesTPane, dashboardLayout.issuesWorkValidation)) {

                JOptionPane.showMessageDialog(null, "llene correctamente los campos",
                        "Atecion", JOptionPane.WARNING_MESSAGE);

            } else {

                Long typeOfWorkFolio = Long.parseLong(dashboardLayout.typeServiceCBox.getSelectedItem().toString());
                Long mechanicFolio = Long.parseLong(dashboardLayout.mechanicCBox.getSelectedItem().toString());
                String plaque = dashboardLayout.vehicleCBox.getSelectedItem().toString();
                Long clientFolio = Long.parseLong(dashboardLayout.clientCBox.getSelectedItem().toString());

                TypeOfWork typeOfService = typeOfWorkService.getTypeOfWorkByFolio(typeOfWorkFolio);
                Mechanic mechanic = mechanicService.getMechanicByFolio(mechanicFolio);
                Vehicle vehicle = vehicleService.getVehicleByPlaque(plaque);
                double price = Double.parseDouble(dashboardLayout.priceServiceField.getText());
                Date dateReceive = dashboardLayout.dateReceive.getDate();
                Date dateSend = dashboardLayout.dateSend.getDate();
                Client client = clientService.getClientByFolio(clientFolio);
                String issues = dashboardLayout.issuesTPane.getText();

                Work work = new Work(typeOfService, mechanic, vehicle, price, dateReceive
                        , dateSend, client, issues);

                workService.createWork(work);

                dashboardLayout.typeServiceCBox.setSelectedIndex(0);
                dashboardLayout.mechanicCBox.setSelectedIndex(0);
                dashboardLayout.vehicleCBox.setSelectedIndex(0);
                dashboardLayout.priceServiceField.setText("");
                dashboardLayout.dateReceive.cleanup();
                dashboardLayout.dateSend.cleanup();
                dashboardLayout.clientCBox.setSelectedIndex(0);
                dashboardLayout.issuesTPane.setText("");
                ((JTextField) dashboardLayout.dateReceive.getDateEditor().getUiComponent()).setText("");
                ((JTextField) dashboardLayout.dateSend.getDateEditor().getUiComponent()).setText("");

                JOptionPane.showMessageDialog(null, "trabajo generado con éxito",
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);

                refreshList();

            }

        }

    }
    
}
