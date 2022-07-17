package com.cydeo.day10_DifferentTypesofAPITesting;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;


public class SSLTestHttpvsHttpsLesson {
    @Test
    public void test1(){
        given()
                .get("https://untrusted-root.badssl.com/")
                .prettyPrint();
    }



    @Test
    public void test2(){

        given().keyStore("","")
                .when().get("url");

    }
}
