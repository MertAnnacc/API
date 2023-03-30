package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C09_GetExpectedDataOlusturma {

     /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine
    bir GET request yolladigimizda donen response body’sinin
    asagida verilen ile ayni oldugunu test ediniz

   Response body :
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */

    @Test
    public void getExpectedData(){

        String url = "https://jsonplaceholder.typicode.com/posts/22";

        JSONObject expBody = new JSONObject();

        expBody.put("userId", 3);
        expBody.put("id", 22);
        expBody.put("title", "dolor sint quo a velit explicabo quia nam");
        expBody.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear" +
                "um mollitia molestiae aut atque rem suscipit\nnam impedit esse");


        Response response = given().when().get(url);

        JsonPath resJsonBody = response.jsonPath();

        Assert.assertEquals(expBody.get("userId"), resJsonBody.getInt("userId"));
        Assert.assertEquals(expBody.get("id"), resJsonBody.getInt("id"));
        Assert.assertEquals(expBody.get("title"), resJsonBody.getString("title"));
        Assert.assertEquals(expBody.get("body"), resJsonBody.getString("body"));



    }
}
