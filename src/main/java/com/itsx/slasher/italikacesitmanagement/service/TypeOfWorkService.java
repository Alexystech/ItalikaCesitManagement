/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service;

import com.itsx.slasher.italikacesitmanagement.model.TypeOfWork;
import java.util.List;

/**
 *
 * @author defin
 */
public interface TypeOfWorkService {
    boolean createTypeOfWork(TypeOfWork typeOfWork);
    boolean removeTypeOfWorkByFolio(long folio);
    boolean updateTypeOfWork(TypeOfWork typeOfWork);
    TypeOfWork getTypeOfWorkByFolio(long folio);
    List<TypeOfWork> getAllTypeOfWorks();
}
