package com.cydeo.day10_DifferentTypesofAPITesting;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest2 extends SpartanTestBase {

    @DisplayName("POST request verify schema")
    @Test
    public void test1() {

        Spartan spartan = new Spartan();

        spartan.setName("Maxximus");
        spartan.setGender("Male");
        spartan.setPhone(8477777777777l);

                given()
                .accept(ContentType.JSON)
                .log()
                .all()
                .and().contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PostRequestSpartanSchema.json"))
                .log().all();
    }
}
