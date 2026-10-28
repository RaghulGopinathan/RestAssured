import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIChain_GetUser {

    @Test
    public void getUser(ITestContext context){

        given()
                .header("Authorization","Bearer d3d30b9d9daa35e6c80ef8b8e57a88bec41bca3b4ffad585012285fc57dfabbf")
        .when()
                .get("https://gorest.co.in/public/v2/users/"+context.getAttribute("context_id"))
        .then()
                .statusCode(200).body("email",equalTo(context.getAttribute("context_email"))).log().body();

    }
}
