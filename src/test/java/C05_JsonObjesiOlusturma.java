import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C05_JsonObjesiOlusturma {

    /*
        Asagidaki JSON Objesini olusturup konsolda yazdirin.
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

    @Test
    public void test01(){

        // ilk olarak inner JSON objesini olusturalim

        JSONObject dateJsonObject = new JSONObject();

        dateJsonObject.put("checkin","2018-01-01");
        dateJsonObject.put("checkout","2019-01-01");

        // outer JSON objesini olusturalim

        JSONObject requestBody = new JSONObject();

        requestBody.put("firstname","Jim");
        requestBody.put("additionalneeds","Breakfast");
        requestBody.put("bookingdates",dateJsonObject);
        requestBody.put("totalprice",111);
        requestBody.put("depositpaid",true);
        requestBody.put("lastname","Brown");

        System.out.println(requestBody);


    }
}
