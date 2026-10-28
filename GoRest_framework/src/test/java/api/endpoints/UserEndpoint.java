package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class UserEndpoint {

    public Response createUser(User user)
    {
        Response response = given()
                .accept(ContentType.JSON).header("Authorization","Bearer "+Routes.BEARER_TOKEN).contentType(ContentType.JSON).body(user)
        .when()
                .post(Routes.USER_POST_URL);

        return response;
    }
    public Response getUser(int userId)
    {
        Response response = given()
                .header("Authorization","Bearer "+Routes.BEARER_TOKEN)
                .accept(ContentType.JSON)
                .pathParams("userId",userId)
        .when()
                .get(Routes.USER_GET_URL);

        return  response;
    }
    public Response deleteUser(int userId){

        Response response = given()
                .header("Authorization","Bearer "+ Routes.BEARER_TOKEN)
                .accept(ContentType.JSON)
                .pathParams("userId",userId)
        .when()
                .delete(Routes.USER_DELETE_URL);

        return response;
    }
}
