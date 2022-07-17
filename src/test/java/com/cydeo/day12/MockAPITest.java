package com.cydeo.day12;

import com.cydeo.pojo.MockAPI;
import com.cydeo.pojo.Students;
import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MockAPITest {






    @Test
    public void test1(){

        given().baseUri("https://2848646d-aa4e-456b-8427-89a983a697a3.mock.pstmn.io")
                .and()
                .accept(ContentType.JSON)
                .when()
                .get("/CustomerAPI")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("firstname", is("John"));



    }

    @Test
    public void test2(){

        Response response = given().baseUri("https://2848646d-aa4e-456b-8427-89a983a697a3.mock.pstmn.io")
                .and()
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .then()
                .statusCode(401)
                .and()
                .header("Date",notNullValue())
                .body("error", is("I don't know you"))
                .extract().response();

        MockAPI mockAPI = response.as(MockAPI.class);

        System.out.println("mockAPI.getBatch() = " + mockAPI.getBatch());



    }








    }

