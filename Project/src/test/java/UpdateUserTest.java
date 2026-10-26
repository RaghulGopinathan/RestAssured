import com.fasterxml.jackson.databind.ser.Serializers;
import com.framework.base.BaseTest;
import com.framework.config.Config;
import com.framework.pojo.UserPojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest extends BaseTest {

    @Test
    public void updateUserTest(){

        UserPojo userPojo = new UserPojo();

        userPojo.setName("Raghul");
        userPojo.setJob("Automation QA");

        given()
                .contentType(ContentType.JSON).body(userPojo)
        .when()
                .put(Config.USER_ENDPOINT+"/2")
        .then()
                .body("job",equalTo("Automation QA")).log().body();
    }
}
