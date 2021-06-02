/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsx.slasher.italikacesitmanagement.model.Work;
import com.itsx.slasher.italikacesitmanagement.service.WorkService;

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
public class WorkServiceImpl implements WorkService {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean createWork(Work work) {

        if ( work == null ) {
            return false;
        }

        final String URL_CREATE_WORK =
                "https://italika-apirest.herokuapp.com/api/work/create/work";

        try {

            mapper.writeValue(new File("target/work.json"), work);

            final HttpRequest createWorkRequest = HttpRequest.newBuilder()
                    .timeout(Duration.ofMinutes(2))
                    .uri(URI.create(URL_CREATE_WORK))
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("target/work.json")))
                    .build();

            HttpResponse<String> response = httpClient
                    .send(createWorkRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeWorkByFolio(long folio) {

        if ( folio > 0 ) {

            final String URL_DELTE_WORK_BY_FOLIO =
                    "https://italika-apirest.herokuapp.com/api/work/delete/work/"+folio;

            final HttpRequest removeWorkRequest = HttpRequest.newBuilder()
                    .timeout(Duration.ofMinutes(2))
                    .uri(URI.create(URL_DELTE_WORK_BY_FOLIO))
                    .DELETE()
                    .build();

            try {

                HttpResponse<String> response = httpClient
                        .send(removeWorkRequest, HttpResponse.BodyHandlers.ofString());

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
    public boolean updateWork(Work work) {

        if ( work == null ) {
            return false;
        }

        final String URL_UPDATE_WORK =
                "https://italika-apirest.herokuapp.com/api/work/update/work";

        try {

            mapper.writeValue(new File("target/work.json"), work);

            final HttpRequest updateWorkRequest = HttpRequest.newBuilder()
                    .timeout(Duration.ofMinutes(2))
                    .uri(URI.create(URL_UPDATE_WORK))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get("target/work.json")))
                    .build();

            HttpResponse<String> response = httpClient
                    .send(updateWorkRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Work getWorkByFolio(long folio) {

        Work work = new Work();

        if ( folio > 0 ) {

            final String URL_GET_WORK_BY_FOLIO =
                    "https://italika-apirest.herokuapp.com/api/work/get/work/"+folio;

            final HttpRequest requestWork = HttpRequest.newBuilder()
                    .timeout(Duration.ofMinutes(2))
                    .uri(URI.create(URL_GET_WORK_BY_FOLIO))
                    .GET()
                    .build();

            try {

                final HttpResponse<String> response = this.httpClient
                        .send(requestWork, HttpResponse.BodyHandlers.ofString());

                work = convertToObject(response.body(), new TypeReference<Work>() {});

                System.out.println(response.statusCode());
                System.out.println(response.body());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

        return work;
    }

    @Override
    public List<Work> getAllWorks() {

        final String URL_GET_ALL_WORKS =
                "https://italika-apirest.herokuapp.com/api/work/get/all/works";

        List<Work> works = new LinkedList<>();

        final  HttpRequest requestWorks = HttpRequest.newBuilder()
                .timeout(Duration.ofMinutes(2))
                .uri(URI.create(URL_GET_ALL_WORKS))
                .GET()
                .build();

        try {

            final HttpResponse<String> response = this.httpClient
                    .send(requestWorks, HttpResponse.BodyHandlers.ofString());

            works = convertToObject(response.body(), new TypeReference<List<Work>>() {});

            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return works;
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
