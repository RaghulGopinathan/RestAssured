package api.test;

import api.endpoints.UserEndpoint;
import api.payloads.UserPayload;
import api.utilities.DataProviderClass;
import api.utilities.XLUtility;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTests {
    UserEndpoint userEndpoint = new UserEndpoint();
    UserPayload userPayload = new UserPayload();

    @Test(priority = 1, dataProviderClass = DataProviderClass.class,dataProvider = "Userdata")
    public void DDCreateUser(String id,String username,String firstName,String lastName,String email, String password, String phone, String userStatus){
        userPayload.setId(Integer.parseInt(id));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPhone(phone);
        userPayload.setUserStatus(Integer.parseInt(userStatus));

        Response response = userEndpoint.createUser(userPayload);
        response.then().log().body();
    }

   @Test(priority = 2,dataProvider = "Usernames",dataProviderClass = DataProviderClass.class)
    public void DDGetUser(String username){
        Response response = userEndpoint.deleteUser(username);
        response.then().statusCode(200);
    }
}
