/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author defin
 */
public class Work implements Serializable {
    
    private Long folio;
    private TypeOfWork typeOfWork;
    private Mechanic mechanic;
    private Vehicle vehicle;
    private Double cost;
    private Date fechaRecibido;
    private Date fechaEntregado;
    private Client client;
    private String issues;

    public Work(TypeOfWork typeOfWork, Mechanic mechanic, Vehicle vehicle, Double cost, Date fechaRecibido, Date fechaEntregado, Client client, String issues) {
        this.typeOfWork = typeOfWork;
        this.mechanic = mechanic;
        this.vehicle = vehicle;
        this.cost = cost;
        this.fechaRecibido = fechaRecibido;
        this.fechaEntregado = fechaEntregado;
        this.client = client;
        this.issues = issues;
    }
    
    public Work() {}

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Date getFechaEntregado() {
        return fechaEntregado;
    }

    public void setFechaEntregado(Date fechaEntregado) {
        this.fechaEntregado = fechaEntregado;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }
    
}
