package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @Test
    public void test1(){

        //just like post request we have differnt options to do something, but lets use map

//here we changed male to female, with put request, you need to write all the other json fields as well or else it wont work,
//  with patch you just write the one you want to change
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("gender", "Female");
        requestMap.put("name", "KingLeonidas");
        requestMap.put("phone", 8877445596l);

        given().contentType(ContentType.JSON)
                .body(requestMap)
                .log()
                .all()
                .and()
                .pathParam("id", 118)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);


    }



    @Test
    public void test3(){

        int idToDelete= 118; //if you want to delete KingLeonidas

        given().pathParam("id", idToDelete)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }



}
