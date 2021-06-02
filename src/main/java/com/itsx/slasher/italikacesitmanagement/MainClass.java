package com.itsx.slasher.italikacesitmanagement;

import com.itsx.slasher.italikacesitmanagement.controller.LoginController;
import com.itsx.slasher.italikacesitmanagement.service.AdministratorService;
import com.itsx.slasher.italikacesitmanagement.service.impl.AdministratorServiceImpl;
import com.itsx.slasher.italikacesitmanagement.view.LoginLayout;

/**
 *
 *
 * @author Alexier
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
