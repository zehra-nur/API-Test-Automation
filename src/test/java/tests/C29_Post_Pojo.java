package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoHerokuAppResponseBody;
import pojos.PojoHerokuappBookingdates;
import pojos.PojoHerokuappRequestBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C29_Post_Pojo extends BaseUrlHerokuapp {

    /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
        gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body                                       Response Body // expected data
                                                           {
        {                                                    "bookingid": 24,
          "firstname" : "Ahmet",                             "booking": {
          "lastname" : “Bulut",                                 "firstname": "Ahmet",
          "totalprice" : 500,                                   "lastname": "Bulut",
          "depositpaid" : false,                                "totalprice": 500,
          "bookingdates" : {                                    "depositpaid": false,
              "checkin" : "2021-06-01",                         "bookingdates": {
              "checkout" : "2021-06-10"                             "checkin": "2021-06-01",
          },                                                        "checkout": "2021-06-10"
          "additionalneeds" : "wi-fi"                           },
        }                                                       "additionalneeds": "wi-fi"
                                                            }
     */

    @Test
    public void test01(){

        // 1- End-point Request body hazirlama
        specHerokuapp.pathParam("pp1","booking");

        PojoHerokuappBookingdates bookingdatesPojo = new PojoHerokuappBookingdates("2021-06-01",
                                                                                  "2021-06-10");

        PojoHerokuappRequestBody requestBodyPojo = new PojoHerokuappRequestBody("Ahmet",
                                                                                "Bulut",
                                                                                500,
                                                                                false,bookingdatesPojo,
                                                                                "wi-fi");

        // 2- Expected data hazirlama

        bookingdatesPojo = new PojoHerokuappBookingdates("2021-06-01", "2021-06-10");

        PojoHerokuappRequestBody bookingPojo = new PojoHerokuappRequestBody("Ahmet",
                                                                            "Bulut",
                                                                            500,
                                                                            false,bookingdatesPojo,
                                                                            "wi-fi");

        PojoHerokuAppResponseBody expectedResponseBodyPojo = new PojoHerokuAppResponseBody(24,bookingPojo);

        /*
           System.out.println(expectedResponseBodyPojo);

           PojoHerokuAppResponseBody{
               bookingid=24,
               booking=PojoHerokuappRequestBody{
                           firstname='Ahmet',
                           lastname='Bulut',
                           totalprice=500,
                           depositpaid=false,
                           bookingdates=PojoHerokuappBookingdates{
                                            checkin='2021-06-01',
                                            checkout='2021-06-10'},
                           additionalneeds='wi-fi'}}

         */

        // 3- Request gonderip, donen response'i kaydetme

        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON)
                            .when().body(requestBodyPojo)
                            .post("{pp1}");

        PojoHerokuAppResponseBody responsePojo = response.as(PojoHerokuAppResponseBody.class);

        // 4- Assertion

        // expectedResponseBodyPojo   <======>   responsePojo
        assertEquals(expectedResponseBodyPojo.getBooking().getFirstname(),responsePojo.getBooking().getFirstname());
        assertEquals(expectedResponseBodyPojo.getBooking().getLastname(),responsePojo.getBooking().getLastname());
        assertEquals(expectedResponseBodyPojo.getBooking().getTotalprice(),responsePojo.getBooking().getTotalprice());
        assertEquals(expectedResponseBodyPojo.getBooking().isDepositpaid(),responsePojo.getBooking().isDepositpaid());
        assertEquals(expectedResponseBodyPojo.getBooking().getAdditionalneeds(),responsePojo.getBooking().getAdditionalneeds());

        assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckin(),
                                 responsePojo.getBooking().getBookingdates().getCheckin());

        assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckout(),
                                 responsePojo.getBooking().getBookingdates().getCheckout());


    }
}
