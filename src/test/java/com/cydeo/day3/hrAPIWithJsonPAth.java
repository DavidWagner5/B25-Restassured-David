package com.cydeo.day3;

import com.cydeo.utilities.HRTstBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class hrAPIWithJsonPAth extends HRTstBase {


    //we want to get brazil
    @Test
    public void test1()  {


        Response response = get("/countries");

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getString("items[3].country_name"));

        //get all country names
        //items.country_name
        List<String> list = jsonPath.getList("items.country_name");

        System.out.println(list);

        //print all one by one
        for (String s : list) {
            System.out.println(s);

        }

        //get me all country names where their region id is 2

        //this is a unique sytanx, we may not use it but who knows
        List<Object> countryListWithID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");

        System.out.println(countryListWithID2);


    }


    @DisplayName("GET request to /employees with param")
    @Test
    public void test2() {

        Response response = given().queryParam("limit", 150)
                .when().get("/employees");

        //get jsonpath object
        JsonPath jsonPath = response.jsonPath();

        //get me all emails who is working as IT_PROG
        List<String> emailList = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(emailList);

        //get me first name of employees who is making more than 10000
        List<String> nameList = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(nameList);
    }
}
