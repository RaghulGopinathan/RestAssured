package api.endpoints;

import api.payloads.UserPayload;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoint {

    public Response createUser(UserPayload userPayload){
        Response response = given()
                .contentType("application/json")
                .accept("application/json")
                .body(userPayload)
        .when()
                .post(Routes.postURL);

        return response;
    }

    public Response getSingleUser(String username){

        Response response =given()
                .pathParams("username",username)
                .when()
                .get(Routes.getURL);

        return response;
    }

    public Response deleteUser(String username){

        Response response = given()
                .pathParams("username",username)
        .when()
                .delete(Routes.deleteURL);

        return response;

    }
}
