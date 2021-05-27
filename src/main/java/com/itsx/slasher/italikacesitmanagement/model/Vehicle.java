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
public class Vehicle implements Serializable{
    
    private String plaque;
    private String brand;
    private String model;
    private Integer year;

    public Vehicle(String brand, String model, Integer year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    public Vehicle() {}

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
}
