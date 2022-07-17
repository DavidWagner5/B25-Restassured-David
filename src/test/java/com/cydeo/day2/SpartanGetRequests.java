package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {


    String url = "http://54.236.55.155:8000";

       /*  GIVEN accept type is application/jason
            WHEN user send GET request to /api/spartans end point
            THEN status code must be 200
            AND response content type must be application/json
         */

    @Test
    public void test1(){


        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url+"/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());


        //how to test api automatically?
        Assertions.assertEquals(200, response.statusCode());

        //how to test content type is application json?
        Assertions.assertEquals("application/json", response.contentType());
    }


    /*GIVEN accept header is application/json
    WHEN user send a get request to /api/spartans/3
    THEN status code must be 200
    AND content type must be application/json
    AND json body should contain Fidole
     */

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url+"/api/spartans/3");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Fidole"));



    }

    @Test
    public void test3(){

        Response response = RestAssured.when().get(url + "/api/hello");

        response.prettyPrint();

        //verify status code
        Assertions.assertEquals(200, response.statusCode());

        //verify the content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //verify data header exists in response headers
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //to get header value we use header() method which accepts header name as parameter and return value as string
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"connection\") = " + response.header("connection"));

        //verify content length is 17
        Assertions.assertEquals("17", response.header("Content-Length"));


        //verify body is "hello from sparta"
        Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }



}
