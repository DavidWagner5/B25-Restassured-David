package com.cydeo.day5;
import com.cydeo.utilities.HRTstBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class Tasks extends HRTstBase {

    @Test
    public void test1(){
//TASK
        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verifty status code and content type
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)

        //expected names
       List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.salary", everyItem(greaterThan(5000)))
                .body("items.first_name", equalTo(names));



    }

    @Test
    public void test2(){
        //chain hamcrest and also get the response object and verify that only 5 names have IT_PROG
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().response().jsonPath();
        //extract methods allow us to get the response object after we use then() method

        assertThat(jsonPath.getList("items.first_name"), hasSize(5));

    }
}
