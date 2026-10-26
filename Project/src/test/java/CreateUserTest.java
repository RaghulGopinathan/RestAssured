import com.framework.base.BaseTest;
import com.framework.config.Config;
import com.framework.pojo.UserPojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest {


    @Test
    public void createUser(){

        UserPojo userPojo = new UserPojo();
        userPojo.setName("Raghul");
        userPojo.setJob("IT");

        given()
                .contentType(ContentType.JSON).body(userPojo)
        .when()
                .post(Config.USER_ENDPOINT)
        .then()
                .statusCode(201).body("name",equalTo("Raghul")).log().body();
    }
}
