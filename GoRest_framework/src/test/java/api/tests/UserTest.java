package api.tests;

import api.endpoints.UserEndpoint;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    UserEndpoint userEndpoint =  new UserEndpoint();;
    Faker faker = new Faker();
    User user = new User();


    @BeforeClass
    public void dataSetup(){
        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setGender("Male");
        user.setStatus("Active");
    }

    @Test(priority = 1)
    public void createUser(){
        Response response = userEndpoint.createUser(user);
        int userId = response.jsonPath().getInt("id");
        user.setUserId(userId);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,201);
    }
    @Test(priority = 2)
    public void getUser(){
        Response response = userEndpoint.getUser(user.getUserId());
        response.then().log().body();
    }

    @Test(priority = 3)
    public void deleteUser(){
        Response response = userEndpoint.deleteUser(user.getUserId());
        response.then().statusCode(204);
    }
}
