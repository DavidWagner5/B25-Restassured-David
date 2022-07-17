package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class BookitExcel {

    //create a method to read bookitqa3 excel file information
    //use those information as an email and password to send a get request to /sign endpoint
    //verify you got 200 for each request
    //print accesstoken for each request
    //https://cybertek-reservation-api-qa3.herokuapp.com

public static List <Map <String, String>> getBookitExcelData(){

    ExcelUtil BookitFile = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");

   List < Map<String, String> >BookitdataMap = BookitFile.getDataList();


    return BookitdataMap;
}


    @ParameterizedTest
    @MethodSource("getBookitExcelData")
    public void excelParamTest(Map<String,String> userInfo){

       Response response = given().accept(ContentType.JSON)
                .queryParams(userInfo)
                .get("https://cybertek-reservation-api-qa3.herokuapp.com/sign")
                .then().statusCode(200).extract().response();


        String accessToken = response.path("accessToken");
        System.out.println(accessToken);


    }
}

