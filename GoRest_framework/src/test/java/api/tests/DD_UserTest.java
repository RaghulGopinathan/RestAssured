package api.tests;

import api.endpoints.UserEndpoint;
import api.payloads.User;
import api.utilities.DD_DataProvider;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DD_UserTest {
    User user = new User();
    UserEndpoint userEndpoint = new UserEndpoint();

    @Test(dataProviderClass = DD_DataProvider.class,dataProvider = "userData")
    public void createUser(String userId, String name, String email, String gender, String status){
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        user.setStatus(status);

        Response response = userEndpoint.createUser(user);
        response.then().log().body().statusCode(201);
    }
}
