package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.AbstractDocument;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class SpartanPostingRequestDemo extends SpartanTestBase {


        /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void test1(){


        String requestBody = " {\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"King Leonidas\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";
        //or you can use serialization by converting a java map to JSON, it will automatically convert from java to JSON

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("gender", "Male");
        requestMap.put("name", "King Leonidas");
        requestMap.put("phone", 8877445596l);


        //or you can use pojo + serialization, but in our custom spartan class, we need to jsonIgnore id
        Spartan spartan = new Spartan();
        spartan.setName("King Leonidas");
        spartan.setGender("Male");
        spartan.setPhone(8877445596l);
        System.out.println(spartan);


      Response response =  given().accept(ContentType.JSON)  //what we are asking from api
                .log()
                .all()
                .and()
                .contentType(ContentType.JSON) //what we are sending to api
                .body(spartan)
                .when()
                .post("/api/spartans");

        //verify
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedMessage));
        assertThat(response.path("data.name"), is("King Leonidas"));
        assertThat(response.path("data.gender"), is("Male"));


        response.prettyPrint();


    }

    @DisplayName("Post request with pojo")
    @Test
    public void test3(){

        Spartan spartan = new Spartan();

        spartan.setName("Maxximus");
        spartan.setGender("Male");
        spartan.setPhone(8477777777777l);

        Response  response = given()
                .accept(ContentType.JSON)
                .log()
                .all()
                .and().contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/api/spartans");


        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        response.prettyPrint();

        //get request

        Response response2 = given().accept(ContentType.JSON)
                .pathParam("id", 123)
                .and().when().get("/api/spartans/{id}");

        Spartan spartanResponse = response2.body().as(Spartan.class);

        System.out.println(spartanResponse.toString());


    }






}
