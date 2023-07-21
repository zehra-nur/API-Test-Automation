package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_BaseUrlHerokuappQueryParam extends BaseUrlHerokuapp {

    /*
       1- https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
          yazarak “firstname” degeri “Eric” olan rezervasyon oldugunu test edecek bir GET
          request gonderdigimizde, donen response’un status code’unun 200 oldugunu ve “Eric”
          ismine sahip en az bir booking oldugunu test edin

        2- https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
           yazarak “firstname” degeri “Jim” ve “lastname” degeri “Jackson” olan rezervasyon
           oldugunu test edecek bir GET request gonderdigimizde, donen response’un status
           code’unun 200 oldugunu ve “Jim Jackson” ismine sahip en az bir booking oldugunu test
           edin
     */

    @Test
    public void test01(){

        /*
           1- https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
              yazarak “firstname” degeri “Susan” olan rezervasyon oldugunu test edecek bir GET
              request gonderdigimizde, donen response’un status code’unun 200 oldugunu ve “Eric”
              ismine sahip en az 3 booking oldugunu test edin
         */

        // 1- endpoint ve request body olustur

        specHerokuapp
                .pathParam("pp1","booking")
                .queryParam("firstname","Susan");

        // 2- expected data olustur

        // 3- request gönder ve response'i kaydet

        Response response = given()
                              .when().spec(specHerokuapp)
                              .get("/{pp1}");

        // 4- assertion

        response
                .then()
                   .assertThat()
                      .statusCode(200)
                      .body("bookingid", Matchers.hasSize(3));
    }
    @Test
    public void test02(){
        /*
          2- https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
             yazarak “firstname” degeri “Jim” ve “Jones” degeri “Doe” olan rezervasyon
             oldugunu test edecek bir GET request gonderdigimizde, donen response’un status
             code’unun 200 oldugunu ve “Jim Jones” ismine sahip en az bir booking oldugunu test
             edin
         */

        // 1- endpoint ve request body olustur

        specHerokuapp
                .pathParam("pp1","booking")
                .queryParams("firstname","Jim","lastname","Jones");

        // 2- expected data olustur

        // 3- request gönder ve response'i kaydet

        Response response = given().spec(specHerokuapp)
                            .when()
                            .get("/{pp1}");

        // assertion

        response
                .then()
                  .assertThat()
                    .statusCode(200)
                    .body("booking",Matchers.hasSize(2));
    }
}
