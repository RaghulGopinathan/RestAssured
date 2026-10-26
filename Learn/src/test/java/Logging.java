import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Logging {

    @Test (priority = 1)
    void loggingCookies(){
        given()
                .when()
                    .get("https://www.google.com/")
                .then()
                    .log().cookies();
    }

    @Test (priority = 2)
    void loggingHeaders(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .log().headers();
    }

    @Test (priority = 3)
    void loggingBody(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .log().body();
    }
}
