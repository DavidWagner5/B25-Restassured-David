package com.cydeo.day11;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class CsvSourceParameterizedTest {

    // like 1/3/4  1+3=4


    @ParameterizedTest
    @CsvSource({"1, 3, 4",
            "2 ,3, 5",
            "8, 7, 15",
            "10, 9, 19"})
    public void testAddition(int num1, int num2, int sum){

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);
        //you can assert with hamcrewst too
        MatcherAssert.assertThat(num1+num2, Matchers.equalTo(sum));


    }



    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({"NY,New York",
                "CO, Denver",
                "VA, Fairfax",
                "VA, Arlington",
                "MA, Boston",
                "MD, Annapolis"})
    public void testMultipleNames(String state, String city) {

                Response response = given().contentType(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when()
                .get("https://api.zippopotam.us/us/{state}/{city}");
        List<String> placeNames = response.jsonPath().getList("places.'place name'");
        assertThat(placeNames, everyItem(containsStringIgnoringCase(city)));
        System.out.println("placeNames.size() = " + placeNames.size());



    }
}
