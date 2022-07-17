package com.cydeo.day3;

import com.cydeo.utilities.HRTstBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class HRAPIwithPath extends HRTstBase {


//limit method
    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}").when().get("/countries");

        //response.prettyPrint();

        System.out.println(response.path("limit").toString());

        System.out.println(response.path("limit").toString());

        System.out.println(response.path("hasMore").toString());

        System.out.println(response.path("items[1].country_id").toString());

        System.out.println(response.path("items[3].country_name").toString());

        //get me all couyntry names
        List<String> countryNames = response.path("items.country_name");
        System.out.println(countryNames);


        //assert that in the response all region_ids are 2
        //save all the regions ids inside the list
        List<Integer>  allRegionsIDs = response.path("items.region_id");

        //assert one by one that they are equal to 2
        for (Integer regionsID : allRegionsIDs) {
            assertEquals(2,regionsID);
        }







    }


    @DisplayName("GET request to /employees with Query Params")
    @Test
    public void test2(){

       Response response =  given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}").when().get("/employees");


        assertEquals(200, response.statusCode());


        //assert all the jobids are IT_PROG
        List<String> allJobIds = response.path("items.job_id");

        //verify each of them is IT_PROG, it will iterate all of the jobids and now will find the ones with IT_PROG
        for (String jobID : allJobIds) {
            assertEquals("IT_PROG", jobID);



        }


    }







    }



