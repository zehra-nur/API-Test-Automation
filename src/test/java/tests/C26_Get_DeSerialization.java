package tests;

import baseUrl.BaseUrlDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_datalari.TestDataDummy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Get_DeSerialization extends BaseUrlDummy {
    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine
        bir GET request gonderdigimizde donen response’un
          status code’unun 200,
          content Type’inin application/json
          ve body’sinin asagidaki gibi oldugunu test edin.

          Response Body
             {
                "status": "success",
                "data": {
                    "id": 3,
                    "employee_name": "Ashton Cox",
                    "employee_salary": 86000,
                    "employee_age": 66,
                    "profile_image": ""
                },
                "message": "Successfully! Record has been fetched."
             }
     */

    @Test
    public  void test01(){

        // 1- endpoint ve request body olustur

        specDummy.pathParams("pp1","employee","pp2",3);

        // 2- expected data olustur

        Map<String,Object> expectedData = TestDataDummy.bodyOlusturMap();

        // 3- request gonder, donen response'i kaydet

        Response response = given().spec(specDummy)
                             .when()
                             .get("/{pp1}/{pp2}");

        Map<String,Object> responseMap = response.as(HashMap.class);

        // 4- assertions

        assertEquals(TestDataDummy.basariliSorguStatusCode,response.statusCode());
        assertEquals(TestDataDummy.contentType,response.contentType());

        assertEquals(expectedData.get("status"),responseMap.get("status"));
        assertEquals(expectedData.get("message"),responseMap.get("message"));

        assertEquals(((Map)expectedData.get("data")).get("id"),
                     ((Map)responseMap.get("data")).get("id"));

        assertEquals(((Map)expectedData.get("data")).get("employee_name"),
                     ((Map)responseMap.get("data")).get("employee_name"));

        assertEquals(((Map)expectedData.get("data")).get("employee_salary"),
                     ((Map)responseMap.get("data")).get("employee_salary"));

        assertEquals(((Map)expectedData.get("data")).get("employee_age"),
                     ((Map)responseMap.get("data")).get("employee_age"));

        assertEquals(((Map)expectedData.get("data")).get("profile_image"),
                     ((Map)responseMap.get("data")).get("profile_image"));
    }
}
