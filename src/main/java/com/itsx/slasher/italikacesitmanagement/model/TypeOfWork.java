/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.model;

import java.io.Serializable;

/**
 *
 * @author defin
 */
public class TypeOfWork implements Serializable {
    
    private Long folio;
    private String nameOfWork;

    public TypeOfWork(Long folio, String nameOfWork) {
        this.folio = folio;
        this.nameOfWork = nameOfWork;
    }

    public TypeOfWork(String nameOfWork) {
        this.nameOfWork = nameOfWork;
    }

    public TypeOfWork() {}
    
    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public String getNameOfWork() {
        return nameOfWork;
    }

    public void setNameOfWork(String nameOfWork) {
        this.nameOfWork = nameOfWork;
    }
    
    
    
}
