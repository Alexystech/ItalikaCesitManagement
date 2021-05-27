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
public class Mechanic implements Serializable {
    private Long folio;
    private String name;
    private String lastName;
    private String motherLastName;
    private String speciality;
    private String cellphone;

    public Mechanic(Long folio, String name, String lastName, String motherLastName, String speciality, String cellphone) {
        this.folio = folio;
        this.name = name;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.speciality = speciality;
        this.cellphone = cellphone;
    }

    public Mechanic(String name, String lastName, String motherLastName, String speciality, String cellphone) {
        this.name = name;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.speciality = speciality;
        this.cellphone = cellphone;
    }
    
    public Mechanic() {}

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    
}
