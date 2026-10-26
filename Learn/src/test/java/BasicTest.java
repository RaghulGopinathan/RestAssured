import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BasicTest {
    int id=0;
    @Test (priority = 1)
    void getUsers(){
        given()
                .when().get("https://reqres.in/api/users");
    }

    @Test (priority = 2)
    void addUsers(){
        Map map = new HashMap();
        map.put("email","Raghul@gmail.com");
        map.put("first_name","Raghul");
        map.put("last_name","G");
        map.put("avatar","https://reqres.in/img/faces/1-image.jpg");

        int id = given().contentType("application/json").body(map).when().post("https://reqres.in/api/users").jsonPath().getInt("id");
    }

    @Test (priority = 3, dependsOnMethods = {"addUsers"})
    void updateUsers(){
        Map map = new HashMap();
        map.put("email","Raghul@gmail.com");
        map.put("first_name","Raghul");
        map.put("last_name","Gopinathan");
        map.put("avatar","https://reqres.in/img/faces/1-image.jpg");

        given().contentType("application/json").body(map).when().put("https://reqres.in/api/users/"+id).then().log().body();
    }

    @Test (priority = 4)
    void deleteUsers(){
        given().when().delete("https://reqres.in/api/users/"+id).then();
    }

}
