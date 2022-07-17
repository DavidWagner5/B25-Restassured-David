package com.cydeo.day11;

import com.cydeo.utilities.SpartanAuthNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class OldRestAssuredTest extends SpartanAuthNewBase {

    @Test
    public void getAllSpartans(){

                 given()
                .accept(ContentType.JSON)
                .and()
                .auth()
                .basic("admin", "admin")
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .and()
                .body("id[0]", is(8))
                         .body("id[1]", is(150))
                         .log().all();


    }
}
