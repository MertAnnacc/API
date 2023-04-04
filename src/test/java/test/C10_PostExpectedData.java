package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C10_PostExpectedData {
         /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
    	                Request body
    	           {
    	                "firstname" : "Ahmet",
    	                "lastname" : “Bulut",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }


    	            	Response Body
    	           {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                             }
                    }
         */

    @Test
    public void postExpData(){
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject innerBody = new JSONObject();

        innerBody.put("checkin", "2021-06-01");
        innerBody.put("checkout", "2021-06-10");


        JSONObject reqBody = new JSONObject();

        reqBody.put("firstname", "Ahmet");
        reqBody.put("lastname", "Bulut");
        reqBody.put("totalprice", 500);
        reqBody.put("depositpaid", false);
        reqBody.put("bookingdates", innerBody);
        reqBody.put("additionalneeds", "wi-fi");



        JSONObject expBody = new JSONObject();

        expBody.put("bookingid", 24);
        expBody.put("booking", reqBody);


        Response response = given()
                                    .contentType(ContentType.JSON).
                            when()
                                    .body(reqBody.toString())
                                    .post(url);


        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expBody.getJSONObject("booking").get("firstname"), jsonPath.get("booking.firstname"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("lastname"), jsonPath.get("booking.lastname"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("totalprice"), jsonPath.get("booking.totalprice"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("depositpaid"), jsonPath.get("booking.depositpaid"));
        Assert.assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"), jsonPath.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"), jsonPath.get("booking.bookingdates.checkout"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("additionalneeds"), jsonPath.get("booking.additionalneeds"));

    }
}
