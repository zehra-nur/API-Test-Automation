package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_datalari.TestDataHerokuapp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Post_TestDataKullanimi extends BaseUrlHerokuapp {
    /*
       https://restful-booker.herokuapp.com/booking url’ine
       asagidaki body'ye sahip bir POST request gonderdigimizde
         donen response’un
         id haric asagidaki gibi oldugunu test edin.

         Request body                                     Response Body
         {                                                {
            "firstname" : "Ahmet",                           "bookingid": 24,
            "lastname" : “Bulut",                            "booking": {
            "totalprice" : 500,                                 "firstname": "Ahmet",
            "depositpaid" : false,                              "lastname": "Bulut",
            "bookingdates" : {                                  "totalprice": 500,
               "checkin" : "2021-06-01",                        "depositpaid": false,
               "checkout" : "2021-06-10"                        "bookingdates": {
            },                                                       "checkin": "2021-06-01",
            "additionalneeds" : "wi-fi"                              "checkout": "2021-06-10"
         }                                                      }
                                                                "additionalneeds": "wi-fi"
                                                             }
                                                           }

     */
    @Test
    public void test01(){

        // 1- endpoint ve request body olustur

        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody = TestDataHerokuapp.jsonRequestBodyOlustur();

        // 2- expected data olustur

        JSONObject expectedData = TestDataHerokuapp.jsonResponseBodyOlustur();

        // 3- request gonder, donen response'i kaydet

        Response response = given().contentType(ContentType.JSON)
                            .when().spec(specHerokuapp).body(requestBody.toString())
                            .post("/{pp1}");

        JsonPath responseJsonPath = response.jsonPath();

        // 4- assertion

        assertEquals(expectedData.getJSONObject("booking").getString("firstname"),
                                               responseJsonPath.getString("booking.firstname"));

        assertEquals(expectedData.getJSONObject("booking").getString("lastname"),
                                               responseJsonPath.getString("booking.lastname"));

        assertEquals(expectedData.getJSONObject("booking").getInt("totalprice"),
                                               responseJsonPath.getInt("booking.totalprice"));

        assertEquals(expectedData.getJSONObject("booking").getBoolean("depositpaid"),
                                               responseJsonPath.getBoolean("booking.depositpaid"));

        assertEquals(expectedData.getJSONObject("booking").getString("additionalneeds"),
                                               responseJsonPath.getString("booking.additionalneeds"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),
                     responseJsonPath.getString("booking.bookingdates.checkin"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),
                     responseJsonPath.getString("booking.bookingdates.checkout"));
    }
}
