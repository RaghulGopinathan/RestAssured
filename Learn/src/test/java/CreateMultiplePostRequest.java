import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreateMultiplePostRequest {
    //@Test
    public void postUsingHashMap(){
        Map data = new HashMap();
        data.put("name","Raghul");
        data.put("age",22);
        data.put("grade","12th");
        String subjects[] = {"Computer Science","Maths"};
        data.put("subjects",subjects);

        given().contentType("application/json").body(data)
                .when()
                    .post("http://localhost:3000/students")
                .then()
                    .statusCode(201)
                    .body("name",equalTo("Raghul"))
                    .body("age",equalTo(22))
                    .body("grade",equalTo("12th"))
                    .body("subjects[0]",equalTo("Computer Science"))
                    .body("subjects[1]",equalTo("Maths")).log().body();
    }

    //@Test
    public void postUsingJsonObject(){
        JSONObject obj = new JSONObject();
        obj.put("name","Yuva");
        obj.put("age",22);
        obj.put("grade","12th");

        String[] subjects = {"Finance"};
        obj.put("subjects",subjects);

        given()
                .contentType("application/json")
                .body(obj.toString())
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201)
                .header("Content-Type","application/json")
                .log().body();

    }

    //@Test
    void postUsingPOJO(){

        POJO_StudentJson pojoObj = new POJO_StudentJson();
        pojoObj.setName("Priya");
        pojoObj.setAge(20);
        pojoObj.setGrade("10th");

        String[] subjects = {"Maths"};
        pojoObj.setSubjects(subjects);

        given()
                .contentType("application/json")
                .body(pojoObj)
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201).log().body();

    }

    @Test
    void postUsingFile() throws FileNotFoundException {
        File file = new File("C:\\Users\\raghul.g01\\OneDrive - Infosys Limited\\HUB Tickets\\Personal\\API-Testing-RestAssured\\resources\\inputJSON.json");
        FileReader fileReader = new FileReader(file);

        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject jsonObject = new JSONObject(jsonTokener);

        given()
                .contentType("application/json").body(jsonObject.toString())
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201)
                .log().body();
    }
}
