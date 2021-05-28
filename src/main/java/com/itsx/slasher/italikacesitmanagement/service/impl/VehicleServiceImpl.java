/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsx.slasher.italikacesitmanagement.model.Vehicle;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;

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
public class VehicleServiceImpl implements VehicleService{

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean createVehicle(Vehicle vehicle) {
        if ( vehicle == null ) {
            return false;
        }

        final String URL_CREATE_VEHICLE =
                "https://italika-apirest.herokuapp.com/api/vehicle/create/vehicle";

        try {
            mapper.writeValue(new File("target/vehicle.json"), vehicle);

            final HttpRequest createVehicleRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_CREATE_VEHICLE))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("target/vehicle.json")))
                    .build();

            HttpResponse<String> response = httpClient.send(createVehicleRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
            return true;
        } catch ( IOException | InterruptedException e ) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeVehicleByPlaque(String plaque) {

        if ( !plaque.trim().equals("") && plaque.length() == 7 ) {

            final String URL_DELETE_VEHICLE_BY_PLAQUE =
                    "https://italika-apirest.herokuapp.com/api/vehicle/delete/vehicle/"+plaque;

            final HttpRequest removeVehicleByPlaque = HttpRequest.newBuilder()
                    .uri(URI.create(URL_DELETE_VEHICLE_BY_PLAQUE))
                    .timeout(Duration.ofMinutes(2))
                    .DELETE()
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(removeVehicleByPlaque, HttpResponse.BodyHandlers.ofString());

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
    public boolean updateVehicle(Vehicle vehicle) {
        if ( vehicle == null ) {
            return false;
        }

        final String URL_UPDATE_VEHICLE =
                "https://italika-apirest.herokuapp.com/api/vehicle/update/vehicle";

        try {

            mapper.writeValue(new File("target/vehicle.json"),vehicle);

            final HttpRequest updateVehicleRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_UPDATE_VEHICLE))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get("target/Vehicle.json")))
                    .build();

            HttpResponse<String> response = httpClient.send(updateVehicleRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Vehicle getVehicleByPlaque(String plaque) {

        Vehicle vehicle = new Vehicle();

        if ( !plaque.trim().equals("") && plaque.length() == 7 ) {
            final String URL_GET_VEHICLE_BY_PLAQUE =
                    "https://italika-apirest.herokuapp.com/api/vehicle/get/vehicle/"+plaque;

            final  HttpRequest requestVehicle = HttpRequest.newBuilder()
                    .timeout(Duration.ofMinutes(2))
                    .GET()
                    .uri(URI.create(URL_GET_VEHICLE_BY_PLAQUE))
                    .build();

            try {
                final HttpResponse<String> httpResponse = this.httpClient
                        .send(requestVehicle, HttpResponse.BodyHandlers.ofString());

                vehicle = convertToObject(httpResponse.body(), new TypeReference<Vehicle>() {});

                System.out.println(httpResponse.statusCode());
                System.out.println(httpResponse.body());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        final String URL_GET_ALL_VEHICLES =
                "https://italika-apirest.herokuapp.com/api/vehicle/get/all/vehicles";
        List<Vehicle> vehicles = new LinkedList<>();

        final HttpRequest requestVehicles = HttpRequest.newBuilder()
                .timeout(Duration.ofMinutes(2))
                .uri(URI.create(URL_GET_ALL_VEHICLES))
                .GET()
                .build();

        try {
            final HttpResponse<String> httpResponse = this.httpClient
                    .send(requestVehicles, HttpResponse.BodyHandlers.ofString());

            vehicles = convertToObject(httpResponse.body(), new TypeReference<List<Vehicle>>() {});
            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return vehicles;
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
