import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookieValidations {

    //@Test(priority = 1)
    void cookieValidation() {

        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie("AEC", "Aaa9EJr7_YQaOmXmzKvop_Z5s-lOLokVq5xD3fb02kOvkR4TDe9pDEqqkw");

    }

    //@Test (priority = 2)
    void storeSingleCookie() {
        String cookie = given()
                .when()
                .get("https://www.google.com/").getCookie("AEC");

        System.out.println(cookie);
    }

    @Test(priority = 3)
    void storeMultipleCookie() {
        Response response = given()
                .when()
                .get("https://www.google.com/");

        System.out.println(response.getCookie("AEC"));
        Map<String, String> cookie_list = response.getCookies();
        System.out.println(cookie_list.keySet());

        for (String x : cookie_list.keySet()) {
            System.out.println(x+" = "+cookie_list.get(x));
        }
    }
}