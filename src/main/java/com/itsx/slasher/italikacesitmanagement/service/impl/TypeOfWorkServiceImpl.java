package com.itsx.slasher.italikacesitmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsx.slasher.italikacesitmanagement.model.TypeOfWork;
import com.itsx.slasher.italikacesitmanagement.service.TypeOfWorkService;

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

public class TypeOfWorkServiceImpl implements TypeOfWorkService {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean createTypeOfWork(TypeOfWork typeOfWork) {

        if ( typeOfWork == null ) {
            return false;
        }

        final String URL_CREATE_TYPE_OF_WORK =
                "https://italika-apirest.herokuapp.com/api/typeofwork/create/typeofwork";

        try {
            mapper.writeValue(new File("target/typeOfWork.json"), typeOfWork);

            final HttpRequest createTypeOfWorkRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_CREATE_TYPE_OF_WORK))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("target/typeOfWork.json")))
                    .build();

            HttpResponse<String> response = httpClient.send(createTypeOfWorkRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeTypeOfWorkByFolio(long folio) {

        if ( folio > 0 ) {

            final String URL_DELETE_TYPE_OF_WORK =
                    "https://italika-apirest.herokuapp.com/api/typeofwork/delete/typeofwork/"+folio;

            final HttpRequest removeTypeOfWorkByFolio = HttpRequest.newBuilder()
                    .uri(URI.create(URL_DELETE_TYPE_OF_WORK))
                    .timeout(Duration.ofMinutes(2))
                    .DELETE()
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(removeTypeOfWorkByFolio, HttpResponse.BodyHandlers.ofString());
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
    public boolean updateTypeOfWork(TypeOfWork typeOfWork) {

        if ( typeOfWork == null ) {
            return false;
        }

        final String URL_UPDATE_TYPE_OF_WORK =
                "https://italika-apirest.herokuapp.com/api/typeofwork/update/typeofwork";

        try {
            mapper.writeValue(new File("target/typeOfWork.json"),typeOfWork);

            final HttpRequest updateTypeOfWorkRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL_UPDATE_TYPE_OF_WORK))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get("target/TypeOfWork.json")))
                    .build();

            HttpResponse<String> response = httpClient.send(updateTypeOfWorkRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public TypeOfWork getTypeOfWorkByFolio(long folio) {

        TypeOfWork typeOfWork = new TypeOfWork();

        if ( folio > 0 ) {

            final String URL_GET_TYPE_OF_WORK_BY_FOLIO =
                    "https://italika-apirest.herokuapp.com/api/typeofwork/get/typeofwork/"+folio;

            final HttpRequest requestTypeOfWork = HttpRequest.newBuilder()
                    .timeout(Duration.ofMinutes(2))
                    .GET()
                    .uri(URI.create(URL_GET_TYPE_OF_WORK_BY_FOLIO))
                    .build();

            try {

                final HttpResponse<String> httpResponse = this.httpClient
                        .send(requestTypeOfWork, HttpResponse.BodyHandlers.ofString());

                typeOfWork = convertToObject(httpResponse.body(), new TypeReference<TypeOfWork>() {});

                System.out.println(httpResponse.statusCode());
                System.out.println(httpResponse.body());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

        return typeOfWork;
    }

    @Override
    public List<TypeOfWork> getAllTypeOfWorks() {

        final String URL_GET_ALL_TYPE_OF_WORK =
                "https://italika-apirest.herokuapp.com/api/typeofwork/get/all/typeofworks";
        List<TypeOfWork> typeOfWorks = new LinkedList<>();

        final HttpRequest requestTypeOfWorks = HttpRequest.newBuilder()
                .timeout(Duration.ofMinutes(2))
                .GET()
                .uri(URI.create(URL_GET_ALL_TYPE_OF_WORK))
                .build();

        try {
            final HttpResponse<String> httpResponse = this.httpClient
                    .send(requestTypeOfWorks, HttpResponse.BodyHandlers.ofString());

            typeOfWorks = convertToObject(httpResponse.body(), new TypeReference<List<TypeOfWork>>() {});
            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return typeOfWorks;
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



