package com.cydeo.day4;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CTrainingTestsHERE {


    //send a get request to student id 24103 as a path parameter and accept header application/json
    //verify status code=200
    // /content type=application/json;charset=UTF-8
    // /Content-Encoding = gzip
    //verify Date header exists
    //assert that
            /*
                firstName Karole
                batch 7
                major Master of Creative Arts
                emailAddress hassan.lebsack@hotmail.com
                companyName Legacy Integration Analyst
                street 6253 Willard Place
                zipCode 28524

                using JsonPath
             */

    @Test
    public void init(){

        //postman get = /student/:id and in value bos 21403
        RestAssured.baseURI ="http://api.cybertektraining.com";

        Response response = given().accept(ContentType.JSON)
                .and().pathParams("studentId", 24103).when().get("/student/{studentId}");
        //the first parameter in path param doesn't matter, but it has to match the get statement curly brace

        JsonPath jsonPath = response.jsonPath();

        //verify status code
        assertEquals(response.statusCode(), 200);
        //verify content type
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //verify a custom header or any header from postman
        assertEquals("gzip", response.header("Content-Encoding"));

        //how to verify the header exists or not
        assertTrue(response.headers().hasHeaderWithName("Date"));


        //view in postman
        //students[0].firstName --> 0 is pointing the first full json object,
        //students[0].batch --> is going down to batch
        //students[0].contact.emailAddress --> there is no array which means this part isnt another json object
        //[]==>array need index, {}==>no array no need index (edited)
        //students[0].company.companyName --> will return company name
        //students[0].company.address.street --> will get the street


        assertEquals("Karole", jsonPath.getString("students[0].firstName"));
        assertEquals(7, jsonPath.getInt("students[0].batch"));
        assertEquals("Master of Creative Arts", jsonPath.getString("students[0].major"));
        assertEquals("hassan.lebsack@hotmail.com", jsonPath.getString("students[0].contact.emailAddress"));
        assertEquals("Legacy Integration Analyst", jsonPath.getString("students[0].company.companyName"));
        assertEquals("6253 Willard Place", jsonPath.get("students[0].company.address.street"));
        assertEquals("Regional Implementation Associate", jsonPath.getString("students[0].company.title"));
        assertEquals("310.026.1705", jsonPath.getString("students[0].contact.phone"));


    }


}
