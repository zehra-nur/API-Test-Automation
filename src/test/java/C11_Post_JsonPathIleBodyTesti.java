import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class C11_Post_JsonPathIleBodyTesti {

    /*
        https://restful-booker.herokuapp.com/booking url’ine
        asagidaki body'ye sahip bir POST request
        gonderdigimizde

          {
           "firstname" : "Zehra",
           "lastname" : “Nur",
           "totalprice" : 500,
           "depositpaid" : false,
           "bookingdates" : {
                "checkin" : "2023-01-10",
                "checkout" : "2023-01-20"
           },
           "additionalneeds" : "Breakfast"
          }

         donen Response’un,
             status code’unun 200,
             content type’inin application-json,

         ve response body’sindeki
             "firstname“in,"Zehra",
             "lastname“in, "Nur",
             "totalprice“in,500,
             "depositpaid“in,false,
             "checkin" tarihinin 2023-01-10
             "checkout" tarihinin 2023-01-20
             "additionalneeds“in,"Breakfast"
             oldugunu test edin
     */

    @Test
    public void test01(){

        // 1- End-point Request body hazirlama
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();

        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin","2023-01-10");
        bookingDates.put("checkout","2023-01-20");

        requestBody.put("firstname","Zehra");
        requestBody.put("lastname","Nur");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingDates);
        requestBody.put("additionalneeds","Breakfast");

        // 2- Expected data hazirlama

        // 3- Request gonderip, donen response'i kaydetme

        Response response = given().contentType(ContentType.JSON)
                             .when().body(requestBody.toString())
                             .post(url);

        // 4- Assertion

        response.then().assertThat().statusCode(200)
                                    .contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Zehra"),
                        "booking.lastname",equalTo("Nur"),
                        "booking.totalprice",equalTo(500),
                        "booking.depositpaid",equalTo(false),
                        "booking.bookingdates.checkin",equalTo("2023-01-10"),
                        "booking.bookingdates.checkout",equalTo("2023-01-20"),
                        "booking.additionalneeds",equalTo("Breakfast"));

    }
}
