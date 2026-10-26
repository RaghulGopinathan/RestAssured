import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeaderValidations {

    //@Test
    void headerValidation(){

        given()
                .when()
                    .get("https://www.google.com/")
                .then()
                    .header("Content-Type","text/html; charset=ISO-8859-1");
    }

    @Test
    void storeSingleHeader(){
        String header = given()
                .when()
                    .get("https://www.google.com/").getHeader("Content-Type");

        System.out.println(header);
    }

    @Test
    void storeMultipleHeaders(){
        Headers headerList = given()
                .when()
                    .get("https://www.google.com/").getHeaders();

        for(Header i : headerList)
        {
            System.out.println(i+" = "+headerList.getValue(i.toString()));
        }
    }
}