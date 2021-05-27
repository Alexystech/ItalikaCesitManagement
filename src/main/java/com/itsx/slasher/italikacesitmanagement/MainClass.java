/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement;

import com.itsx.slasher.italikacesitmanagement.controller.LoginController;
import com.itsx.slasher.italikacesitmanagement.model.Administrator;
import com.itsx.slasher.italikacesitmanagement.model.Client;
import com.itsx.slasher.italikacesitmanagement.service.AdministratorService;
import com.itsx.slasher.italikacesitmanagement.service.ClientService;
import com.itsx.slasher.italikacesitmanagement.service.impl.AdministratorServiceImpl;
import com.itsx.slasher.italikacesitmanagement.service.impl.ClientServiceImpl;
import com.itsx.slasher.italikacesitmanagement.view.DashboardLayout;
import com.itsx.slasher.italikacesitmanagement.view.LoginLayout;
import java.util.List;
import javax.swing.WindowConstants;

/**
 *
 * @author defin
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        LoginLayout loginLayout = new LoginLayout();
        AdministratorService administratorService = new AdministratorServiceImpl();
        
        LoginController loginController = new LoginController(loginLayout,administratorService);
        loginController.start();
        loginController.runValidations();
        
    }
    
}
