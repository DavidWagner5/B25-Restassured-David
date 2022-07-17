package com.cydeo.day11;



import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class CsvFileSourceParameterizedTest {

    //write a parameterized test for this request
    //get the data from csv source
    //get https://api.zippopotam.us/us/{state}/{city}
    //verify each place has zipcount

    @ParameterizedTest
    @CsvFileSource(resources = "/postalcode.csv", numLinesToSkip = 1)
    public void zipCodeTestWithFile(String state, String city, int ZipCount){

        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("ZipCount = " + ZipCount);


        given().accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when()
                .get("https://api.zippopotam.us/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places", hasSize(ZipCount));



    }





}
