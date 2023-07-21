package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_datalari.TestDataJasonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C20_Get_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {
    /*
         https://jsonplaceholder.typicode.com/posts/22 url'ine
         bir GET request yolladigimizda
         donen response’in
            status kodunun 200 ve
            response body’sinin asagida verilen ile ayni oldugunu test ediniz

               Response body :
               {
                  "userId": 3,
                  "id": 22,
                  "title": "dolor sint quo a velit explicabo quia nam",
                  "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
               }
     */

    @Test
    public void test01(){

        // 1- endpoint ve request body olustur

        specJsonPlaceholder.pathParams("pp1","posts","pp2","22");

        // 2- expected data olustur

        JSONObject expectedData = TestDataJasonPlaceholder.responseBodyOlustur22();

       // 3- request gonder, donen response'i kaydet

        Response response = given().spec(specJsonPlaceholder)
                            .when()
                            .get("/{pp1}/{pp2}");

        JsonPath responseJsonPath = response.jsonPath();

        // 4- assertion

        assertEquals(TestDataJasonPlaceholder.basariliSorguStatusCode,response.statusCode());
        assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));



    }
}
