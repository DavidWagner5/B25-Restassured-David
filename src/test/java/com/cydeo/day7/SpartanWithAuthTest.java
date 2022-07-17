package com.cydeo.day7;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanWithAuthTest extends SpartanAuthTestBase {

    //providing no username and no password will get you 401, unauthorized user.
// IF you log in as a user and try to put,patch, delete then you will get 403 - forbidden
//IF you log in as an admin and try to put, patch, therefore authorization is passed you will get 200 - OK
@DisplayName("GET /api/spartans as a public user")
    @Test
    public void test1(){
    given()
            .accept(ContentType.JSON)
            .when()
            .get("/api/spartans")
            .then()
            .statusCode(401)  //401 means unauthorized user
            .log()
            .all()
            .body("error", is("Unauthorized"));


}


@DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
    //how to provide admin admin as username and password

    given()
            .auth()
            .basic("admin", "admin")
            .and()
            .accept(ContentType.JSON)
            .log()
            .all()
            .when()
            .get("/api/spartans")
            .then()
            .statusCode(200)
            .log()
            .all();
}


@DisplayName("DELETE /spartans[id] as editor user expect 403")

@Test
public void testEditor(){
    given()
            .auth().basic("editor","editor")
            .and().accept(ContentType.JSON)
            .and().pathParam("id",40)
            .when()
            .delete("/api/spartans/{id}")
            .then().statusCode(403).log().all();


}



@Test
    public void test4(){

    given()
            .auth()
            .basic("user", "user")
            .and()
            .accept(ContentType.JSON)
            .and().pathParam("id",40)
            .log()
            .all()
            .when()
            .delete("/api/spartans/{id}")
            .then()
            .statusCode(403);

}




}
