
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C16_Put_SoftAssertIleExpectedDataTesti {
    /*
       http://dummy.restapiexample.com/api/v1/update/21 url’ine
       asagidaki body’ye sahip bir PUT request gonderdigimizde
       donen response’un asagidaki gibi oldugunu test edin.

       Request Body                                   Response Body
        {                                          {
           "status": "success",                       "status": "success",
           "data": {                                  "data": {
               "name": “Ahmet",                           "status": "success",
               "salary": "1230",                          "data": {
               "age": "44",                                   "name": “Ahmet",
               "id": 40                                       "salary": "1230",
           }                                                  "age": "44",
        }                                                     "id": 40 }
                                                      },
                                                      "message": "Successfully! Record has been updated."
                                                    }
     */

    @Test
    public void test01(){

        // 1- End-point Request body hazirlama

        String url = "http://dummy.restapiexample.com/api/v1/update/21";

        JSONObject requestBody = new JSONObject();
        JSONObject data = new JSONObject();

        data.put("name", "Ahmet");
        data.put("salary", "1230");
        data.put("age", "44");
        data.put("id", 40);

        requestBody.put("status", "success");
        requestBody.put("data", data);

        // 2- Expected data hazirlama

        JSONObject expectedData = new JSONObject();

        expectedData.put("status", "success");
        expectedData.put("data", requestBody);
        expectedData.put("message", "Successfully! Record has been updated.");

        // 3- Request gonderip, donen response'i kaydetme

        Response response = given().contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .put(url);

        response.prettyPrint();

        // 4- Assertion

        SoftAssert softAssert = new SoftAssert();

        JsonPath responseJsonPath = response.jsonPath();

        softAssert.assertEquals(responseJsonPath.get("status"), expectedData.get("status"));

        softAssert.assertEquals(responseJsonPath.get("message"), expectedData.get("message"));

        softAssert.assertEquals(responseJsonPath.get("data.status"),
                                expectedData.getJSONObject("data").get("status"));

        softAssert.assertEquals(responseJsonPath.get("data.data.name"),
                                expectedData.getJSONObject("data").getJSONObject("data").get("name"));

        softAssert.assertEquals(responseJsonPath.get("data.data.salary"),
                                expectedData.getJSONObject("data").getJSONObject("data").get("salary"));

        softAssert.assertEquals(responseJsonPath.get("data.data.age"),
                                expectedData.getJSONObject("data").getJSONObject("data").get("age"));

        softAssert.assertEquals(responseJsonPath.get("data.data.id"),
                                expectedData.getJSONObject("data").getJSONObject("data").get("id"));

        softAssert.assertAll();
    }
}
