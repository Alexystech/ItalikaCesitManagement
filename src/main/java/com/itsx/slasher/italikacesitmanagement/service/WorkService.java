/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service;

import com.itsx.slasher.italikacesitmanagement.model.Work;
import java.util.List;

/**
 *
 * @author defin
 */
public interface WorkService {
    boolean createWork(Work work);
    boolean removeWorkByFolio(long folio);
    boolean updateWork(Work work);
    Work getWorkByFolio(long folio);
    List<Work> getAllWorks();
}
