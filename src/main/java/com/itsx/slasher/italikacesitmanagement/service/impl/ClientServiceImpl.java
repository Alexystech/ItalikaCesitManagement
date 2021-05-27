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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
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

        if ( client == null ) {
            return false;
        }

        final String URL_CREATE_CLIENT =
                "https://italika-apirest.herokuapp.com/api/client/create/client";

        try {

            mapper.writeValue(new File("target/client.json"), client);

            final HttpRequest createClientRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_CREATE_CLIENT))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("target/client.json")))
                    .build();

            HttpResponse<String> response = httpClient.send(createClientRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeClientByFolio(long folio) {

        if ( folio > 0 ) {

            final String URL_DELETE_CLIENT =
                    "https://italika-apirest.herokuapp.com/api/client/delete/client/"+folio;

            final HttpRequest removeClientRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_DELETE_CLIENT))
                    .timeout(Duration.ofMinutes(2))
                    .DELETE()
                    .build();

            try {

                HttpResponse<String> response = httpClient.send(removeClientRequest, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.statusCode());
                System.out.println(response.body());

                return true;
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    @Override
    public boolean updateClient(Client client) {

        if ( client == null ) {
            return false;
        }

        final String URL_UPDATE_CLIENT =
                "https://italika-apirest.herokuapp.com/api/client/update/client";

        try {

            mapper.writeValue(new File("target/client.json"), client);

            final HttpRequest updateClientRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_UPDATE_CLIENT))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get("target/client.json")))
                    .build();

            HttpResponse<String> response = httpClient.send(updateClientRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Client getClientByFolio(long folio) {

        Client client = new Client();

        if ( folio > 0 ) {

            final String URL_GET_CLIENT_BY_FOLIO =
                    "https://italika-apirest.herokuapp.com/api/client/get/client/"+folio;

            final HttpRequest requestClient = HttpRequest.newBuilder()
                    .timeout(Duration.ofMinutes(2))
                    .uri(URI.create(URL_GET_CLIENT_BY_FOLIO))
                    .GET()
                    .build();

            try {

                final HttpResponse<String> httpResponse = this.httpClient
                        .send(requestClient, HttpResponse.BodyHandlers.ofString());

                client = convertToObject(httpResponse.body(), new TypeReference<Client>() {});

                System.out.println(httpResponse.statusCode());
                System.out.println(httpResponse.body());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

        return client;
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

            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.body());
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
