package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRTstBase {


    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.236.55.155:1000/ords/hr";
    }


}
