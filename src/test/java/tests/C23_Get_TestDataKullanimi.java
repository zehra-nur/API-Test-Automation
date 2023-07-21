package tests;

import baseUrl.BaseUrlDummy;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_datalari.TestDataDummy;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C23_Get_TestDataKullanimi extends BaseUrlDummy {
    /*
       http://dummy.restapiexample.com/api/v1/employee/3 url’ine
       bir GET request gonderdigimizde
       donen response’un
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
    public void test01(){

        // endpoint ve request body olustur

        specDummy.pathParams("pp1","employee","pp2","3");

        // expected data olustur

        JSONObject expectedData = TestDataDummy.
                                  jsonResponseBodyOlustur(3,
                                               "Ashton Cox",
                                                86000,
                                                  66,
                                                   "");

        // request gonder, donen response'i kaydet

        Response response = given().spec(specDummy)
                            .when()
                            .get("/{pp1}/{pp2}");

        JsonPath responseJsonPath = response.jsonPath();

        // assertions

        assertEquals(TestDataDummy.basariliSorguStatusCode,response.statusCode());

        assertEquals(TestDataDummy.contentType,response.contentType());

        assertEquals(expectedData.getString("status"),responseJsonPath.getString("status"));

        assertEquals(expectedData.getString("message"),responseJsonPath.getString("message"));

        assertEquals(expectedData.getJSONObject("data").getInt("id"),
                                            responseJsonPath.getInt("data.id"));

        assertEquals(expectedData.getJSONObject("data").getString("employee_name"),
                                            responseJsonPath.getString("data.employee_name"));

        assertEquals(expectedData.getJSONObject("data").getInt("employee_salary"),
                                            responseJsonPath.getInt("data.employee_salary"));

        assertEquals(expectedData.getJSONObject("data").getInt("employee_age"),
                                            responseJsonPath.getInt("data.employee_age"));

        assertEquals(expectedData.getJSONObject("data").getString("profile_image"),
                                            responseJsonPath.getString("data.profile_image"));



    }
}
