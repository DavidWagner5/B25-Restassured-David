package com.cydeo.day10_DifferentTypesofAPITesting;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanXMLTest extends SpartanAuthTestBase{
    //we will ask for xml result
    //assrt sdtatus code
    //assert content type xml
    //verify first name
//in postman its gonna be List.item[0].name to get the first name

    @Test
    public void test1(){
        given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/xml")
                .body("List.item[0].name", is("Rodolfo"))
                .log().all();


    }
//or

    @DisplayName("GET all spartan with XML")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when().get("/api/spartans");


        XmlPath xmlPath = response.xmlPath();

        String name = xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);

        //get the 3rd spartan
        int id = xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

        List<String> names = xmlPath.getList("List.item.name");
        System.out.println("names = " + names);


    }
}
