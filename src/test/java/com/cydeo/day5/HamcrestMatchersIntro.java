package com.cydeo.day5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

//hamcrest in an assertion library, like assertions, but easier, we will be learning the different types of assertions
public class HamcrestMatchersIntro {



    @DisplayName("Assertions with Numbers")
    @Test
    public void test1(){


        assertThat(5+5,is(10));
        assertThat(5+5, equalTo(10));
        assertThat(5+5, is((equalTo(10))));
        assertThat(5+5, is((not(equalTo(10)))));


        //greaterThan()
        //greaterThanOREqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5, is(greaterThan(9)));


    }

    //string assertions
    @DisplayName("Assertions with String")
    @Test
    public void test2(){

        String text = "B25 is learning Hamcrest";


        assertThat(text, is("B25 is learning Hamcrest"));



        //check if this text starts with B25
        assertThat(text,startsWith("B25"));
        //case insensitive
        assertThat(text,startsWithIgnoringCase("b25"));
        //ends with
        assertThat(text,endsWith("rest"));

        //check if text contains String learning

        assertThat(text,containsString("learning"));
        //with ignoring case
        assertThat(text,containsStringIgnoringCase("LEARNING"));

        String str ="   ";

        //check if above str is blank
        assertThat(str,blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());
    }


    @DisplayName("Hamcrest for Collection")

    @Test
    public void testCollection(){

        List<Integer> listofNumbers = Arrays.asList(1,4,5,6,32,54,66,43,12,312,35);

        //check size of list
        assertThat(listofNumbers, hasSize(11));

        //check has item
        assertThat(listofNumbers, hasItem(54));

        //check if this list hasItems 6,54,43
        assertThat(listofNumbers,hasItems(6,54,43));

        //check if all numbers are greater than 0
        assertThat(listofNumbers,everyItem(greaterThan(0)));
    }

    }

