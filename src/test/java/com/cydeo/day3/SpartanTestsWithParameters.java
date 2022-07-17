package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters extends SpartanTestBase {

        /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */


    @DisplayName("Get request to /api/spartans")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).and()
                .pathParams("id", 5).when().get("/api/spartans/{id}");


        //verify status code
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //verify "Blythe" inside the payload
        assertTrue(response.body().asString().contains("Blythe"));
    }

     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).and().
                pathParams("id", 500).when().get("/api/spartans/{id}");

        assertEquals(404, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Not Found"));


    }


       /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @DisplayName("GET request to /api.spartan/search with Query Params")
    @Test
    public void test3(){

//log and all won't affect the response, but it will return what is happening in the console for help
        Response response = given().log().all().accept(ContentType.JSON)
                //.queryParams("gender","Female" , "nameContains","e")
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","e")
                .when().get("/api/spartans/search");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println(response.asString());
        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));
    }


    //OR


    @Test
    public void test3again(){

        //create a map and store query params information
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains", "e");

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .queryParams(queryMap)
                .when().get("/api/spartans/search");
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println(response.asString());
        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));
    }









    }



















