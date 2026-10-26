import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
// Core REST Assured methods (given, when, get, post, etc.)
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.XMLObject;

import java.util.List;

import static io.restassured.RestAssured.*;

// XML/JSON matchers specific to REST Assured
import static io.restassured.matcher.RestAssuredMatchers.*;

// Hamcrest matchers for assertions (equalTo, hasItems, lessThan, etc.)
import static org.hamcrest.Matchers.*;


public class ParsingXMLResponseBody {
    @Test
    void getUsers()
    {
        given()
                .when()
                    .get("https://gorest.co.in/public/v2/users.xml")
                .then()
                    .log().body();
    }

    @Test
    void validatingUsingRestAssuredMethods(){
        given().when().get("https://gorest.co.in/public/v2/users.xml").then().statusCode(200).body("objects.object[0].name",equalTo("Hari Khatri Esq"));
    }

    @Test
    void storingUsingRestAssuredMethods(){
       Response response =  given().when().get("https://gorest.co.in/public/v2/users.xml");
        String name = response.xmlPath().get("objects.object[0].name").toString();
        //Assert.assertEquals(name,"Hari Khatri Esq");

        List<String> nameList = response.xmlPath().getList("objects.object.name");
        System.out.println(nameList);
    }

}