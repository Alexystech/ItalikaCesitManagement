/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsx.slasher.italikacesitmanagement.model.Mechanic;
import com.itsx.slasher.italikacesitmanagement.service.MechanicService;

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
public class MechanicServiceImpl implements MechanicService {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean createMechanic(Mechanic mechanic) {

        if ( mechanic == null ) {
            return false;
        }

        final String URL_CREATE_MECHANIC =
                "https://italika-apirest.herokuapp.com/api/mechanic/create/mechanic";

        try {

            mapper.writeValue(new File("target/mechanic.json"), mechanic);

            final HttpRequest createMechanicRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_CREATE_MECHANIC))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("target/mechanic.json")))
                    .build();

            HttpResponse<String> response = httpClient.send(createMechanicRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
            return true;
        } catch ( IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeMechanicByFolio(long folio) {

        if ( folio > 0 ) {

            final String URL_DELETE_MECHANIC =
                    "https://italika-apirest.herokuapp.com/api/mechanic/delete/mechanic/"+folio;

            final HttpRequest removeMechanic = HttpRequest.newBuilder()
                    .uri(URI.create(URL_DELETE_MECHANIC))
                    .timeout(Duration.ofMinutes(2))
                    .DELETE()
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(removeMechanic, HttpResponse.BodyHandlers.ofString());

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
    public boolean updateMechanic(Mechanic mechanic) {

        if ( mechanic == null ) {
            return false;
        }

        final String URL_UPDATE_MECHANIC =
                "https://italika-apirest.herokuapp.com/api/mechanic/update/mechanic";

        try {

            mapper.writeValue(new File("target/mechanic.json"),mechanic);

            final HttpRequest updateMechanicRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_UPDATE_MECHANIC))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get("target/Mechanic.json")))
                    .build();

            HttpResponse<String> response = httpClient.send(updateMechanicRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Mechanic getMechanicByFolio(long folio) {

        Mechanic mechanic = new Mechanic();

        if ( folio > 0 ) {

            final String URL_GET_MECHANIC_BY_FOLIO =
                    "https://italika-apirest.herokuapp.com/api/mechanic/get/mechanic/"+folio;

            final HttpRequest requestMechanic = HttpRequest.newBuilder()
                    .timeout(Duration.ofMinutes(2))
                    .GET()
                    .uri(URI.create(URL_GET_MECHANIC_BY_FOLIO))
                    .build();

            try {

                final HttpResponse<String> httpResponse = this.httpClient
                        .send(requestMechanic, HttpResponse.BodyHandlers.ofString());

                mechanic = convertToObject(httpResponse.body(), new TypeReference<Mechanic>() {});

                System.out.println(httpResponse.statusCode());
                System.out.println(httpResponse.body());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return mechanic;
    }

    @Override
    public List<Mechanic> getAllMechanics() {

        final String URL_GET_ALL_MECHANICS =
                "https://italika-apirest.herokuapp.com/api/mechanic/get/all/mechanics";
        List<Mechanic> mechanics = new LinkedList<>();

        final HttpRequest requestMechanics = HttpRequest.newBuilder()
                .timeout(Duration.ofMinutes(2))
                .GET()
                .uri(URI.create(URL_GET_ALL_MECHANICS))
                .build();

        try {
            final HttpResponse<String> httpResponse = this.httpClient
                    .send(requestMechanics, HttpResponse.BodyHandlers.ofString());

            mechanics = convertToObject(httpResponse.body(), new TypeReference<List<Mechanic>>() {});
            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return mechanics;
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
