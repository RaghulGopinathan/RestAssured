import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponeBody {

    @Test
    void validatingUsingRestMethods()
    {
        given()
                .when()
                    .get("https://reqres.in/api/users")
                .then()
                    .statusCode(200)
                    .body("data[0].email",equalTo("george.bluth@reqres.in"));
    }

    @Test
    void storingUsingRestAssured(){
       Response res = given()
                .when()
                    .get("https://reqres.in/api/users");

       String email = res.jsonPath().get("data[0].email");
       System.out.println(email);

       //You can fetch the list of emails using restAssured method (jsonPath())
       List<String> list = res.jsonPath().getList("data.email");
        System.out.println(list);


    }

    //You can also fetch the list of email using org.JSON dependency.
    @Test
    void parsingThroughEntireResponseWithJsonDependency(){
        Response res = given()
                        .when()
                            .get("https://reqres.in/api/users");

        System.out.println(res);
        JSONObject obj = new JSONObject(res.asString());

        for (int i=0;i<obj.getJSONArray("data").length();i++)
        {
            String emailValue = obj.getJSONArray("data").getJSONObject(i).get("email").toString();
            System.out.println(emailValue);
        }
    }
}
