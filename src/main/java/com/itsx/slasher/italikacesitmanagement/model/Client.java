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
public class Client implements Serializable {
    
    private Long folio;
    private String name;
    private String lastName;
    private String motherLastName;
    private String cellphone;

    public Client(Long folio, String name, String lastName, String motherLastName, String cellphone) {
        this.folio = folio;
        this.name = name;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.cellphone = cellphone;
    }

    public Client(String name, String lastName, String motherLastName, String cellphone) {
        this.name = name;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.cellphone = cellphone;
    }
    
    public Client() {}

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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    
    
    
}
