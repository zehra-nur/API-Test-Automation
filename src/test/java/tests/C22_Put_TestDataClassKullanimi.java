package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_datalari.TestDataJasonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Put_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {
    /*
       https://jsonplaceholder.typicode.com/posts/70 url'ine
       asagidaki body'e sahip bir PUT request yolladigimizda
       donen response’in
         status kodunun 200,
         content type’inin “application/json; charset=utf-8”,
         Connection header degerinin “keep-alive”
         ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

           Request Body                             Expected Data :
             {                                          {
               "title": "Ahmet",                          "title": "Ahmet",
               "body": "Merhaba",                         "body": "Merhaba",
               "userId": 10,                              "userId": 10,
               "id": 70                                   "id": 70
             }                                          }
     */
    @Test
    public void test01(){

        // 1- endpoint ve request body olustur

        specJsonPlaceholder.pathParams("pp1","posts","pp2","70");

        JSONObject requestBody = TestDataJasonPlaceholder.responseJsonBodyOlustur(10,70,"Ahmet","Merhaba");

        // 2- expected data olustur

        JSONObject expectedData = TestDataJasonPlaceholder.responseJsonBodyOlustur(10,70,"Ahmet","Merhaba");

        // 3- request gonder ve donen response'i kaydet

        Response response = given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .put("/{pp1}/{pp2}");

        JsonPath responseJsonPath = response.jsonPath();
        // 4- assertion

        assertEquals(TestDataJasonPlaceholder.basariliSorguStatusCode,response.statusCode());
        assertEquals(TestDataJasonPlaceholder.contentType,response.contentType());
        assertEquals(TestDataJasonPlaceholder.headerConnection,response.header("Connection"));
        assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));
    }
}
