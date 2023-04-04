package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C11_GetSofAssertleDataExpected {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

        Response Body
        {
        "status":"success",
        "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
        }

         */

    @Test
    public void getSoftAssert(){

        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        JSONObject innerBody = new JSONObject();

        innerBody.put("id",3);
        innerBody.put("employee_name","Ashton Cox");
        innerBody.put( "employee_salary",86000);
        innerBody.put("profile_image","");

        JSONObject expBody = new JSONObject();

        expBody.put("status","success");
        expBody.put( "message","Successfully! Record has been fetched.");
        expBody.put("data",innerBody);


        Response response = given().when().get(url);

        JsonPath reqJson = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(reqJson.get("status"), expBody.get("status"));
        softAssert.assertEquals(reqJson.get("message"), expBody.get("message"));
        softAssert.assertEquals(reqJson.get("data.id"), expBody.getJSONObject("data").get("id"));
        softAssert.assertEquals(reqJson.get("data.employee_name"), expBody.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(reqJson.get("data.employee_salary"), expBody.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(reqJson.get("data.profile_image"), expBody.getJSONObject("data").get("profile_image"));

        softAssert.assertAll();

    }
}
