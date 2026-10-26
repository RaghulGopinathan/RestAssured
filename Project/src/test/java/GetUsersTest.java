import com.framework.base.BaseTest;
import com.framework.config.Config;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUsersTest extends BaseTest {

    @Test
    public void getUsers(){
        given()
                .queryParams("page",2)
        .when()
                .get(Config.USER_ENDPOINT)
        .then()
                .statusCode(200).body("page",equalTo(2));
    }
}
