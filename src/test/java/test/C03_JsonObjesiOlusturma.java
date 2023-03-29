package test;

import org.json.JSONObject;
import org.junit.Test;

public class C03_JsonObjesiOlusturma {

      /*
    Asagidaki JSON Objesini olusturup konsolda yazdirin.

    {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":1
    }
    */

    @Test
    public void jsonObje1(){
        JSONObject obje1 = new JSONObject();

        obje1.put("title", "Ahmet");
        obje1.put("body", "Merhaba");
        obje1.put("userId", 1);

        System.out.println(obje1);
    }

    @Test
    public void obje2(){
         /*
                {
                 "firstname":"Jim",
                 "additionalneeds":"Breakfast",
                 "bookingdates":{
                         "checkin":"2018-01-01",
                         "checkout":"2019-01-01"
                    },
                  "totalprice":111,
                  "depositpaid":true,
                  "lastname":"Brown"
                  }
         */

        JSONObject innerJsonObje = new JSONObject();

        innerJsonObje.put("checkin","2018-01-01");
        innerJsonObje.put("checkout","2019-01-01");

        JSONObject obje2 = new JSONObject();

        obje2.put("firstname","Jim");
        obje2.put("additionalneeds","Breakfast");
        obje2.put("bookingdates", innerJsonObje);
        obje2.put("totalprice",111);
        obje2.put("depositpaid",true);
        obje2.put("lastname","Brown");

        System.out.println(obje2);
    }

}
