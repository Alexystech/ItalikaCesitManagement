/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service;

import com.itsx.slasher.italikacesitmanagement.model.Mechanic;
import java.util.List;

/**
 *
 * @author defin
 */
public interface MechanicService {
    boolean createMechanic(Mechanic mechanic);
    boolean removeMechanicByFolio(long folio);
    boolean updateMechanic(Mechanic mechanic);
    Mechanic getMechanicByFolio(long folio);
    List<Mechanic> getAllMechanics();
}
