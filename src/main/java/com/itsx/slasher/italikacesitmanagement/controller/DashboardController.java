/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.service.ClientService;
import com.itsx.slasher.italikacesitmanagement.service.MechanicService;
import com.itsx.slasher.italikacesitmanagement.service.TypeOfWorkService;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.view.DashboardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.WindowConstants;

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
        dashboardLayout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
