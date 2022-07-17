package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetRequest {

    //beforeAll is the same thing with beforeClass in testNG
    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://44.201.121.105:8000";
    }

    @Test
    public void test() {



        /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8; */

    Response response = RestAssured.given().accept(ContentType.XML).when().get("/api/spartans/10");

        Assertions.assertEquals(406, response.statusCode());








    }




}
