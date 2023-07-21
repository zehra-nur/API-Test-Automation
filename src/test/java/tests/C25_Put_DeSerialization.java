package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_datalari.TestDataHerokuapp;
import test_datalari.TestDataJasonPlaceholder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Put_DeSerialization extends BaseUrlJsonPlaceholder {
    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine
        asagidaki body'e sahip bir PUT request yolladigimizda
        donen response’in response body’sinin
        asagida verilen ile ayni oldugunu test ediniz

              Request Body :                     Expected Data :
         {                                     {
           "title": "Ahmet",                       "title": "Ahmet",
           "body": "Merhaba",                      "body": "Merhaba",
           "userId": 10,                           "userId": 10,
           "id": 70                                "id": 70
         }                                     }
     */
    @Test
    public void test01(){

        // 1- endpoint ve request body olustur

        specJsonPlaceholder.pathParams("pp1","posts","pp2","70");

        Map<String,Object> requestBody = TestDataJasonPlaceholder.bodyOlusturMap();

        System.out.println(requestBody);

        // 2- expected data olustur

        Map<String,Object> expectedData = TestDataJasonPlaceholder.bodyOlusturMap();

        // 3- request gonder, donen reponse'i kaydet

        Response response = given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                             .when().body(requestBody)
                             .put("/{pp1}/{pp2}");

        // 4- assertion

        // expected response body  <=========>  response
        //      Map                             response

        // Assertion yapabilmemiz icin response'i Map'e cevirmemiz gerekir (De-Serialization).

        Map<String,Object> responseMap =response.as(HashMap.class);

        // expectedData (Map)  <====>  responseMap (Map)

        assertEquals(expectedData.get("title"),responseMap.get("title"));
        assertEquals(expectedData.get("body"),responseMap.get("body"));
        assertEquals(expectedData.get("userId"),responseMap.get("userId"));
        assertEquals(expectedData.get("id"),responseMap.get("id"));

    }
}
