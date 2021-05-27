/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service.impl;

import com.itsx.slasher.italikacesitmanagement.model.Mechanic;
import com.itsx.slasher.italikacesitmanagement.service.MechanicService;
import java.util.List;

/**
 *
 * @author defin
 */
public class MechanicServiceImpl implements MechanicService {

    @Override
    public boolean createMechanic(Mechanic mechanic) {
        return false;
    }

    @Override
    public boolean removeMechanicByFolio(long folio) {
        return false;
    }

    @Override
    public boolean updateMechanic(Mechanic mechanic) {
        return false;
    }

    @Override
    public Mechanic getMechanicByFolio(long folio) {
        return null;
    }

    @Override
    public List<Mechanic> getAllMechanics() {
        return null;
    }
}
