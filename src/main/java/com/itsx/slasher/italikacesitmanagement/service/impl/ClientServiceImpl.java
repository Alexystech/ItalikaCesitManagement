/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsx.slasher.italikacesitmanagement.model.Client;
import com.itsx.slasher.italikacesitmanagement.service.ClientService;
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
public class ClientServiceImpl implements ClientService {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2).build();
    
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean createClient(Client client) {
        return false;
    }

    @Override
    public boolean removeClientByFolio(long folio) {
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        return false;
    }

    @Override
    public Client getClientByFolio(long folio) {
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        
        final String URL_GET_ALL_CLIENTS = 
                "https://italika-apirest.herokuapp.com/api/client/get/all/clients";
        List<Client> clients = new LinkedList<Client>();
        
        final HttpRequest requestClients = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_GET_ALL_CLIENTS))
                .build();
        
        try {
            final HttpResponse<String> httpResponse = this.httpClient
                    .send(requestClients, HttpResponse.BodyHandlers.ofString());
            
            clients = convertToObject(httpResponse.body(), new TypeReference< List<Client> >() {});
            
        } catch ( IOException | InterruptedException e ) {
            e.printStackTrace();
        }
        
        return clients;
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
