package com.cydeo.ApiPractices;

import com.cydeo.pojo.Employee;
import com.cydeo.utilities.HRTstBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class HRTaskGetPojo extends HRTstBase {


    @Test
    public void HrGetRandomRequest(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"manager_id\":103}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        jsonPath.prettyPrint();

        Employee employee107 = jsonPath.getObject("items[3]", Employee.class);

        System.out.println(employee107.getFirstName());

       assertEquals("Diana",(employee107.getFirstName()));


    }
}
