/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service;

import com.itsx.slasher.italikacesitmanagement.model.Administrator;
import java.util.List;

/**
 *
 * @author defin
 */
public interface AdministratorService {
    boolean createAdministrator(Administrator administrator);
    boolean removeAdministratorByFolio(long folio);
    boolean updateAdministrator(Administrator administrator);
    Administrator getAdministratorByFolio(long folio);
    List<Administrator> getAllAdministrators();
}
