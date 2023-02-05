package org.example.api.client;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

abstract class Base {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public RequestSpecification getBaseRequestSpecification() {
        return given().baseUri(BASE_URL)
                .filters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()));
    }


}