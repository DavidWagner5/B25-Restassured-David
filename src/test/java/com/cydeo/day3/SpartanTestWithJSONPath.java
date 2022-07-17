package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithJSONPath extends SpartanTestBase {


    @DisplayName("GET one spartan with JsonPath")
    @Test
    public void test1(){

        Response response =  given()
                .accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        //print the name
        System.out.println(response.path("name").toString());

        //print the name, jspath is saved now, we can get all the info below now
        JsonPath jsonPath = response.jsonPath();


        String name = jsonPath.getString("name");
        System.out.println("name = " + name);

        //save into variables
        int id = jsonPath.getInt("id");
        String name1 = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        //assertion
        assertEquals(10,id);
    }
}
