package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C08_Get_ResponseBodyTestiListKullanimi {
     /*
            http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
            donen Response'in
            status code'unun 200,
            ve content type'inin application/json,
            ve response body'sindeki
                employees sayisinin 24
                ve employee'lerden birinin "Ashton Cox"
                ve girilen yaslar icinde 61,40 ve 30 degerlerinin oldugunu test edin
            test edin.
     */

    @Test
    public void ashton(){

        // 1 - URL hazirla

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response = given().when().get(url);

        // 4 - Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("data.id", hasSize(24),
                        "data.employee_name",hasItem("Ashton Cox"),
                        "data.employee_age",hasItems(61,40,30));
    }
}
