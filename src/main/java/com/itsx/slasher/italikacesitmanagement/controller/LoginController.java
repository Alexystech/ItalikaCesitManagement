/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.controller;

import com.itsx.slasher.italikacesitmanagement.model.Administrator;
import com.itsx.slasher.italikacesitmanagement.service.AdministratorService;
import com.itsx.slasher.italikacesitmanagement.service.ClientService;
import com.itsx.slasher.italikacesitmanagement.service.MechanicService;
import com.itsx.slasher.italikacesitmanagement.service.TypeOfWorkService;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;
import com.itsx.slasher.italikacesitmanagement.service.impl.ClientServiceImpl;
import com.itsx.slasher.italikacesitmanagement.service.impl.MechanicServiceImpl;
import com.itsx.slasher.italikacesitmanagement.service.impl.TypeOfWorkServiceImpl;
import com.itsx.slasher.italikacesitmanagement.service.impl.VehicleServiceImpl;
import com.itsx.slasher.italikacesitmanagement.service.impl.WorkServiceImpl;
import com.itsx.slasher.italikacesitmanagement.validations.LoginValidations;
import com.itsx.slasher.italikacesitmanagement.validations.impl.LoginValidationsImpl;
import com.itsx.slasher.italikacesitmanagement.view.DashboardLayout;
import com.itsx.slasher.italikacesitmanagement.view.LoginLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author defin
 */
public class LoginController implements ActionListener {

    private LoginLayout loginLayout;
    private AdministratorService administratorService;
    
    public LoginController(LoginLayout loginLayout, AdministratorService administratorService) {
        this.administratorService = administratorService;
        
        this.loginLayout = loginLayout;
        this.loginLayout.loginButton.addActionListener(this);
    }
    
    public void start() {
        this.loginLayout.setVisible(true);
    }
    
    public void runValidations() {
        loginLayout.loginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                LoginValidationsImpl loginValidationsImpl = new LoginValidationsImpl();
                loginValidationsImpl.validateLoginUserName(loginLayout.loginField,loginLayout.userNameLoginValidation);
            }
        });

        loginLayout.passwordLoginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                LoginValidationsImpl loginValidationsImpl = new LoginValidationsImpl();
                loginValidationsImpl.validateLoginPassword(loginLayout.passwordLoginField
                        , loginLayout.passwordLoginValidation);
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        if ( arg0.getSource() == loginLayout.loginButton ) {
            
            LoginValidations loginValidations = new LoginValidationsImpl();
            List<Administrator> administrators = administratorService.getAllAdministrators();
            
            if ( !loginValidations.validateLoginUserName(this.loginLayout.loginField, this.loginLayout.userNameLoginValidation) ||
                    !loginValidations.validateLoginPassword(this.loginLayout.passwordLoginField, this.loginLayout.passwordLoginValidation) ||
                    !loginValidation(administrators)) {
                
                JOptionPane.showMessageDialog(null,"Usuario o contraseña no coinciden",
                        "Alerta", JOptionPane.WARNING_MESSAGE);
                
            } else {

                loginLayout.setVisible(false);
                DashboardLayout dashboard = new DashboardLayout();
                
                ClientService clientService = new ClientServiceImpl();
                MechanicService mechanicService = new MechanicServiceImpl();
                TypeOfWorkService typeOfWorkService = new TypeOfWorkServiceImpl();
                VehicleService vehicleService = new VehicleServiceImpl();
                WorkService workService = new WorkServiceImpl();
                
                DashboardController dashboardController = new DashboardController(
                        dashboard, clientService, mechanicService, 
                        typeOfWorkService, vehicleService, workService
                );
                
                dashboardController.startDashboard();
                dashboardController.runValidations();

                Thread threadGraph = new Thread(dashboardController);

                threadGraph.start();
                
            }
            
        }
    }
    
    private boolean loginValidation(List<Administrator> administrators) {
        return administrators.stream()
                .anyMatch(administrator -> administrator.getUserName()
                        .equals(loginLayout.loginField.getText()) && Arrays.equals(administrator
                        .getPassword().toCharArray(), loginLayout.passwordLoginField.getPassword())
                );
    }
    
}
