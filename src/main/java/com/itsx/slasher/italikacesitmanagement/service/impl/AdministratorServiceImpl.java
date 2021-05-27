/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsx.slasher.italikacesitmanagement.model.Administrator;
import com.itsx.slasher.italikacesitmanagement.service.AdministratorService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author defin
 */
public class AdministratorServiceImpl implements AdministratorService {
    
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public boolean createAdministrator(Administrator administrator) {
        
        return false;
    }

    @Override
    public boolean removeAdministratorByFolio(long folio) {
        
        return false;
    }

    @Override
    public boolean updateAdministrator(Administrator administrator) {
        
        return false;
    }

    @Override
    public Administrator getAdministratorByFolio(long folio) {
        return null;
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        
        final String URL_GET_ALL_ADMINISTRATORS = 
                "https://italika-apirest.herokuapp.com/api/administrator/get/all/administrators";
        List<Administrator> administrators = new LinkedList<Administrator>();
        
        final HttpRequest requestAdministrators = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_GET_ALL_ADMINISTRATORS))
                .build();
        
        try {
            final HttpResponse<String> httpResponse = this.httpClient
                    .send(requestAdministrators, HttpResponse.BodyHandlers.ofString());
            
            administrators = convertToObject(httpResponse.body(), new TypeReference< List<Administrator> >() {} );
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        return administrators;
    }
    
    private <T> T convertToObject(String json, TypeReference<T> reference) {
        try {
            return this.mapper.readValue(json, reference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
