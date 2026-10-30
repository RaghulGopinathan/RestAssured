package api.test;

import api.endpoints.UserEndpoint;
import api.payloads.UserPayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserTest {

    UserEndpoint userEndpoint = new UserEndpoint();
    Faker faker;
    UserPayload userPayload;
    public Logger logger;

    @BeforeClass
    public void setupData(){

        userPayload = new UserPayload();
        faker = new Faker();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setUsername(faker.name().username());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().toString());

        logger = LogManager.getLogger(this.getClass());

    }

    @Test (priority = 1)
    public void createUser(){
        logger.info("createUser method started");
        Response response = userEndpoint.createUser(userPayload);
        response.then().statusCode(200).log().all();
        System.out.println(userPayload.getUsername());
        logger.info("createUser method ended");
    }
    @Test (priority = 2)
    public void getSingleUser(){
        logger.info("getSingleUser method started");
       Response response = userEndpoint.getSingleUser(this.userPayload.getUsername());
       response.then().statusCode(200).log().body();
        System.out.println(userPayload.getUsername());
        logger.info("getSingleUser method ended");
    }

    @Test (priority = 3)
    public void deleteUser(){
        logger.info("deleteUser method started");
        System.out.println(userPayload.getUsername());
        Response response = userEndpoint.deleteUser(userPayload.getUsername());
        response.then().statusCode(200);
        logger.info("deleteUser method ended");
    }

}
