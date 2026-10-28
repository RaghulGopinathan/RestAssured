import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APIChain_CreateUser {

    @Test
    public void createUser(ITestContext context){
        Faker faker = new Faker();
        UserPOJO userPOJO = new UserPOJO();

        userPOJO.setName(faker.name().fullName());
        userPOJO.setEmail(faker.internet().emailAddress());
        userPOJO.setGender("Male");
        userPOJO.setStatus("Active");

        Response response = given()
                .contentType("application/json").body(userPOJO)
                .header("Authorization","Bearer d3d30b9d9daa35e6c80ef8b8e57a88bec41bca3b4ffad585012285fc57dfabbf")
        .when()
                .post("https://gorest.co.in/public/v2/users");

        String id = response.jsonPath().get("id").toString();
        String email = response.jsonPath().get("email");

        context.setAttribute("context_id",id);
        context.setAttribute("context_email",email);

    }
}
