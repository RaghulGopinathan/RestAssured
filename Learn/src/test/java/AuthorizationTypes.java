import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AuthorizationTypes {

    @Test
    public void basic(){

        given()
                .auth().basic("postman","password")
        .when()
                .get("http://echo.getpostman.com/basic-auth")
        .then()
                .statusCode(200);
    }

    @Test
    public void digest(){
        given()
                .auth().digest("postman","password")
        .when()
                .get("http://echo.getpostman.com/basic-auth")
        .then()
                .statusCode(200);
    }

    @Test
    public void preemptive(){
        given()
                .auth().preemptive().basic("postman","password")
        .when()
                .get("http://echo.getpostman.com/basic-auth")
        .then()
                .statusCode(200);
    }

    @Test
    public void bearer(){ //NOTE: Bearer token should be passed via header
        Response response =
                given()
                    .header("Authorization","Bearer "+"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")
                .when()
                    .get("https://api.github.com/user/repos");

        List<String> repoList = response.jsonPath().getList("name");
        System.out.println(repoList);

    }

    //@Test
    public void OAuth1()
    {
        given()
                .auth().oauth("Consumer Key","Consumer Secret","Access Token","Secret Token")
                .when()
                .get()
                .then()
                .statusCode(200);
    }
   // @Test
    public void OAuth2()
    {
        given()
                .auth().oauth2("")
        .when()
                .get()
        .then()
                .statusCode(200);
    }
}
