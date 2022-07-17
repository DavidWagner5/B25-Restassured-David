package com.cydeo.day3;
import com.cydeo.utilities.HRTstBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class HRTasks extends HRTstBase {

    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"region_id\":2}")
                .log().all().get("countries");

        assertEquals(response.statusCode(), 200);

        assertEquals("application/json", response.contentType());

        assertTrue(response.asString().contains("United States of America"));



    }


}
