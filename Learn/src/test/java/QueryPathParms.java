import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class QueryPathParms {


    @Test
    public void passQueryAndPathParams()
    {
        given()
                .pathParams("path","users")
                .queryParam("page","2")
                .queryParam("id","8")
        .when()
                .get("https://reqres.in/api/{path}")
        .then()
                .log().all();

    }

}
