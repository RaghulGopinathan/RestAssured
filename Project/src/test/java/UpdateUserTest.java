import com.framework.config.Config;
import com.framework.pojo.UserPojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest {

    @Test
    public void updateUserTest(){

        UserPojo userPojo = new UserPojo();

        userPojo.setName("Raghul");
        userPojo.setJob("Automation QA");

        given()
                .contentType(ContentType.JSON).body(userPojo)
        .when()
                .put(Config.USER_ENDPOINT+"/266")
        .then()
                .body("job",equalTo("Automation QA")).log().body();
    }
}
