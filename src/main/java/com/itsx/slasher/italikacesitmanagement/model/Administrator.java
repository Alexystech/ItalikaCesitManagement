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
public class Administrator implements Serializable {
    
    private Long folio;
    private String name;
    private String lastName;
    private String motherLastName;
    private String userName;
    private String eMail;
    private String password;
    private String cellphone;

    public Administrator(String name, String lastName, String motherLastName, String userName, String eMail, String password, String cellphone) {
        this.name = name;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
        this.cellphone = cellphone;
    }
   
    public Administrator() {}

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    
    
    
}
