package com.cydeo.day8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class BookItTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

    }

    String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTMxMiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.ZIHJuDh19eLga3bLP7udnvNtEA0DM_W1H67ah2Zu3Lc";

    @DisplayName("GET /api/campuses")
    @Test
    public void test1(){

        //how to pass bearer token for bookit?
        //in postman, we need the email and password to get the token, but we already got it from postman

        given()
                .header("Authorization", accessToken)
                .accept(ContentType.JSON)
                .when()
                .get("/api/campuses")
                .then()
                .statusCode(200)
                .log()
                .all();





    }

    @DisplayName("GET")
    @Test
    public void test2(){

        given()
                .header("Authorization",accessToken)
                .accept(ContentType.JSON)
                .when()
                .get("/api/campuses")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    //create bookitutil class
    //create static method that accepts email and password, and return token with "bearer your-token"



}
