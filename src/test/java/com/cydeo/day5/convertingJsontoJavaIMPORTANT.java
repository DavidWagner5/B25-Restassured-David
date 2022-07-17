package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

//DESERIALIZATION
//converting json response to a java collection
//jackson and Gson are libraries to deserialization and serialization. they are also known as objectmapper, jsonparser,databinding libraries,
public class convertingJsontoJavaIMPORTANT extends SpartanTestBase {

    @DisplayName("GET one spartan and deserialize to MAP")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response();

                //get the json body and convert it to java map

        Map<String, Object> jsonMap = response.as(Map.class); //we use this to convert json to java

        System.out.println(jsonMap);

        String name = (String) jsonMap.get("name");
                //verify name is meta
        assertThat(name, is("Meta"));


    }

    @DisplayName("GET all spartans to java data structure")

    @Test
    public void test2(){
         Response response = get("/api/spartans").then().statusCode(200).extract().response();

         //we need to convert json to java

        List<Map<String,Object>> jsonList = response.as(List.class); //we use this to convert json to java,
        // convert to list of map because we need to get the name of the list, and then the key and value of what we want

        System.out.println(jsonList.get(1).get("name"));
    }


}
