package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C18_BaseUrlHerokuapp extends BaseUrlHerokuapp {

    /*
       Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin

       1- https://restful-booker.herokuapp.com/booking endpointine bir GET request gonderdigimizde
          donen response’un status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test
          edin

       2- https://restful-booker.herokuapp.com/booking endpointine asagidaki body’ye sahip
          bir POST request gonderdigimizde donen response’un status code’unun 200 oldugunu ve
          “firstname” degerinin “Ahmet” oldugunu test edin

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
     */
    @Test
    public void test01(){

        /*
           1- https://restful-booker.herokuapp.com/booking endpointine bir GET request gonderdigimizde
              donen response’un status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test
              edin
         */

        // 1- endpoint ve request body olustur

        specHerokuapp.pathParam("pp1","booking");

        // 2- expected data olustur

        // 3- request gonder ve donen response'i kaydet

        Response response = given().spec(specHerokuapp)
                            .when()
                            .get("/{pp1}");

        // 4- assertions

        JsonPath responseJsonPath = response.jsonPath();
        System.out.println(responseJsonPath.getList("bookingid").size());

        response.then()
                .assertThat()
                   .statusCode(200)
                   .body("bookingid", Matchers.hasSize(3368));
    }
    @Test
    public void test02(){

        /*
           2- https://restful-booker.herokuapp.com/booking endpointine asagidaki body’ye sahip
          bir POST request gonderdigimizde donen response’un status code’unun 200 oldugunu ve
          “firstname” degerinin “Ahmet” oldugunu test edin

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
     */

        // 1- end point ve request body olustur

        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin" , "2021-06-01");
        bookingdates.put("checkout" , "2021-06-10");

        requestBody.put("firstname" , "Ahmet");
        requestBody.put("lastname" , "Bulut");
        requestBody.put("totalprice" , 500);
        requestBody.put("depositpaid" , false);
        requestBody.put("bookingdates" , bookingdates);
        requestBody.put("additionalneeds" , "wi-fi");

        // 2- expected data olustur

        // 3- request gonder ve response'i kaydet

        Response response = given()
                               .contentType(ContentType.JSON)
                            .when()
                               .spec(specHerokuapp)
                               .body(requestBody.toString())
                               .post("/{pp1}");

        // 4- assertions

        response
                .then()
                   .assertThat()
                      .statusCode(200)
                      .body("booking.firstname",Matchers.equalTo("Ahmet"));
    }
}