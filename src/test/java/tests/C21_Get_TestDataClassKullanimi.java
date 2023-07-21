package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_datalari.TestDataJasonPlaceholder;

import static io.restassured.RestAssured.given;

public class C21_Get_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {

    /*
        https://jsonplaceholder.typicode.com/posts/40 url'ine
        ir GET request yolladigimizda
        donen response’in
            status kodunun 200 ve
            response body’sinin asagida verilen ile ayni oldugunu test ediniz

               Response body :
                   {
                      "userId": 4,
                      "id": 40,
                      "title": "enim quo cumque",
                      "body": "ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam"
                   }
     */

    @Test
    public void test01(){

        // 1- endpoint ve request body olustur

        specJsonPlaceholder.pathParams("pp1","posts","pp2","40");

        // 2- expected body olustur

        JSONObject expctedData = TestDataJasonPlaceholder
                                                .responseJsonBodyOlustur(4,
                                                                         40,
                                                                         "enim quo cumque",
                                                                         "ut voluptatum aliquid illo tenetur nemo sequi quo facilis" +
                                                                                 "\nipsum rem optio mollitia quas" +
                                                                                 "\nvoluptatem eum voluptas qui" +
                                                                                 "\nunde omnis voluptatem iure quasi maxime voluptas nam");

        // 3- request gonder, donen response'i kaydet

        Response response = given().spec(specJsonPlaceholder)
                            .when()
                            .get("/{pp1}/{pp2}");

        JsonPath responseJsonPath = response.jsonPath();

        // 4- assertion

        Assert.assertEquals(TestDataJasonPlaceholder.basariliSorguStatusCode,response.statusCode());
        Assert.assertEquals(expctedData.getInt("userId"),responseJsonPath.getInt("userId"));
        Assert.assertEquals(expctedData.getInt("id"),responseJsonPath.getInt("id"));
        Assert.assertEquals(expctedData.getString("title"),responseJsonPath.getString("title"));
        Assert.assertEquals(expctedData.getString("body"),responseJsonPath.getString("body"));

    }
}
