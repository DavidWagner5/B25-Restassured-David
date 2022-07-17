package com.cydeo.day6;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.utilities.HRTstBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
public class HRPojoGetRequestTestHERE extends HRTstBase {


        @Test
        public void test1(){
            JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();


            //we want to store second region under items in Region class object
            Region region2 = jsonPath.getObject("items[1]", Region.class);

            System.out.println("region2.getRegion_id() = " + region2.getRegionId());
            System.out.println(region2.getRegion_name());
            System.out.println(region2.getLinks().get(0).getHref());

        }

        @Test
    public void tes2(){

           JsonPath jsonpath =  get("/employees").then().statusCode(200).extract().jsonPath();

           Employee emp1 = jsonpath.getObject("items[0]", Employee.class);

            System.out.println(emp1.getFirstName());
            System.out.println(emp1.getLastName());
            System.out.println(emp1.getJobId());
            System.out.println(emp1.getSalary());


        }


    }


