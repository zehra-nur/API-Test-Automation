package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoJsonPlaceholder;
import test_datalari.TestDataJasonPlaceholder;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;

public class C28_Put_PojoClass extends BaseUrlJsonPlaceholder {

    /*
       https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body'e sahip bir PUT
       request yolladigimizda donen response’in
       Status Code'unun 200,
       Content Type’inin “application/json; charset=utf-8”,
       Connection header degerinin “keep-alive”
       ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

          Request Body                              Response Body // Expected Data :
          {                                        {
            "title": "Ahmet",                         "title": "Ahmet",
            "body": "Merhaba",                        "body": "Merhaba",
            "userId": 10,                             "userId": 10,
            "id": 70                                  "id": 70
          }                                        }
     */

    @Test
    public void test01(){
        // 1- End-point Request body hazirlama
        specJsonPlaceholder.pathParams("pp1","posts","pp2",70);

        PojoJsonPlaceholder requestBodyPojo = new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);

        // 2- Expected data hazirlama

        PojoJsonPlaceholder expextedDataPojo = new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);

        // 3- Request gonderip, donen response'i kaydetme

        Response response = given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                            .when().body(requestBodyPojo)
                            .put("{pp1}/{pp2}");

        PojoJsonPlaceholder responsePojo = response.as(PojoJsonPlaceholder.class);

        // 4- Assertion
        // expected data       <=====>  response
        // expectedDataPojo    <=====>  responsePojo

        // Status Code'unun 200

        assertEquals(TestDataJasonPlaceholder.basariliSorguStatusCode,response.statusCode());

        // Content Type’inin “application/json

        assertEquals(TestDataJasonPlaceholder.contentType,response.contentType());

        // Connection header degerinin “keep-alive”

        assertEquals(TestDataJasonPlaceholder.headerConnection,response.header("Connection"));

        // response body’sinin asagida verilen ile ayni oldugunu test ediniz

        assertEquals(expextedDataPojo.getTitle(),responsePojo.getTitle());
        assertEquals(expextedDataPojo.getBody(),responsePojo.getBody());
        assertEquals(expextedDataPojo.getId(),responsePojo.getId());
        assertEquals(expextedDataPojo.getUserId(),responsePojo.getUserId());



    }

}
