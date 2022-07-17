package com.cydeo.day5;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
//HERE WE ARE COMBINING SQL DATABASE, JDBC UTILS, API, AND HEMCREST

public class SpartanAPIvsDB extends SpartanTestBase {
    // we want to automate api vs database testing with maven project.
    // so we have api dependencies ready for api request
    // we can send get request to /api/spartans endpoint
    // to retrieve individual api results. then we can store it in map<String,
    // Object> with the help of jackson library and as() method from restassured

    @DisplayName("Compare one spartan information api vs DB")
    @Test
    public void test(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", "15")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();

        //convert json response to map
        Map<String, Object> apiMap = response.as(Map.class);
        System.out.println(apiMap.toString());
        //we need to get information form DB

        String query = "SELECT SPARTAN_ID, NAME, GENDER, PHONE\n" +
                "FROM SPARTANS\n" +
                "WHERE SPARTAN_ID = 15";

        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);

        //compare api vs db
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));




    }

}
