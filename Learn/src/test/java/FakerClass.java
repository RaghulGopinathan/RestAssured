import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FakerClass {

    @Test
    void fakerData(){

        Faker faker = new Faker();

        JSONObject jo = new JSONObject();

        jo.put("name",faker.name().fullName());
        jo.put("job",faker.job().title());

        given()
                .contentType("application/json").body(jo.toString())
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201).log().body();
    }
}