import com.framework.base.BaseTest;
import com.framework.config.Config;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteUserTest extends BaseTest {

    @Test
    public void deleteUser(){

        given()

        .when()
                .delete(Config.USER_ENDPOINT+"/2")
        .then()
                .statusCode(204);
    }

}
