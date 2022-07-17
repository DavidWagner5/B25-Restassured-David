package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //JUNIT5
    //in testNG, before class is the opposite
    @BeforeAll
    public static void init(){

        System.out.println("Before all is runing");
    }

    //beforeMethod is testng version of beforeEach, same logic
    @BeforeEach
    public void initEach(){
        System.out.println("\tBefore each is running");
    }

    @AfterEach
    public void closeEach(){
        System.out.println("\tAfter each is running");
    }


    @Test
    public void test1(){
        System.out.println("Test 1 is running");

    }

    @Disabled //skips a test
    @Test
    public void test2(){

        System.out.println("Test 2 is running");


    }


    @AfterAll
    public static void init2(){

        System.out.println("After class is running");
    }
}
