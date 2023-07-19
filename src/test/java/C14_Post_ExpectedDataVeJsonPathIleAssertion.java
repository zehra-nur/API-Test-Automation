import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C14_Post_ExpectedDataVeJsonPathIleAssertion {
    /*
       https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
       gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

            Request body                                       Response Body

           {                                                 {
               "firstname" : "Zehra",                            "bookingid": 24,
               "lastname" : “Nur",                               "booking": {
               "totalprice" : 500,                                   "firstname": "Zehra",
               "depositpaid" : true,                                 "lastname": "Nur",
               "bookingdates" : {                                    "totalprice": 500,
                      "checkin" : "2021-06-01",                      "depositpaid": true,
                      "checkout" : "2021-06-10"                      "bookingdates": {
                     },                                                      "checkin": "2021-06-01",
               "additionalneeds" : "wi-fi"                                   "checkout": "2021-06-10"
           }                                                      },
                                                                 "additionalneeds": "wi-fi"
                                                                 }
                                                              }
    */
    @Test
    public void test01(){
        // 1- End-point Request body hazirlama

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin" , "2021-06-01");
        bookingdates.put("checkout" , "2021-06-10");

        requestBody.put("firstname" , "Zehra");
        requestBody.put("lastname" , "Nur");
        requestBody.put("totalprice" , 500);
        requestBody.put("depositpaid" , true);
        requestBody.put("bookingdates" , bookingdates);
        requestBody.put("additionalneeds" , "wi-fi");

        // 2- Expected data hazirlama

        JSONObject expectedData = new JSONObject();

        expectedData.put("bookingid", 24);
        expectedData.put("booking", requestBody);

        System.out.println(expectedData.toString());

        // 3- Request gonderip, donen response'i kaydetme

        Response response = given().contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .post(url);

        // 4- Assertion

        JsonPath responseJsonPath = response.jsonPath();

        // ilk yazilan expected =====> olusturdugumuz JsonObject : expectedData
        // ikinci yazilan actual

        assertEquals(expectedData.getJSONObject("booking").get("firstname"),
                     responseJsonPath.get("booking.firstname"));

        assertEquals(expectedData.getJSONObject("booking").get("lastname"),
                     responseJsonPath.get("booking.lastname"));

        assertEquals(expectedData.getJSONObject("booking").get("totalprice"),
                     responseJsonPath.get("booking.totalprice"));

        assertEquals(expectedData.getJSONObject("booking").get("depositpaid"),
                     responseJsonPath.get("booking.depositpaid"));

        assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"),
                     responseJsonPath.get("booking.additionalneeds"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                     responseJsonPath.get("booking.bookingdates.checkin"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                     responseJsonPath.get("booking.bookingdates.checkout"));

    }
}
