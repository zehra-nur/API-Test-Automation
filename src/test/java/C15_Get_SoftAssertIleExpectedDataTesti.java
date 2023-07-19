import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C15_Get_SoftAssertIleExpectedDataTesti {

    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
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

        // 1- End-point Request body hazirlama

        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        // 2- Expected data hazirlama

        JSONObject expectedResponseBody = new JSONObject();
        JSONObject data = new JSONObject();

        data.put("id", 3);
        data.put("employee_name", "Ali Can");
        data.put("employee_salary", 86000);
        data.put("employee_age", 36);
        data.put("profile_image", "");

        expectedResponseBody.put("status", "success");
        expectedResponseBody.put("data", data);
        expectedResponseBody.put("message", "Successfully! Record has been fetched.");

        // 3- Request gonderip, donen response'i kaydetme

        Response response = given().when().get(url);

        response.prettyPrint();
        // 4- Assertion

        // oncelikle response uzerindeki bilgileri kolay almak icin
        // JsonPath'e cast edelim

        JsonPath responseJsonPath = response.jsonPath();

        // Assertion'lari SoftAssert ile yapalim

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseJsonPath.get("status"),expectedResponseBody.get("status"));
        softAssert.assertEquals(responseJsonPath.get("message"),expectedResponseBody.get("message"));

        softAssert.assertEquals(responseJsonPath.get("data.id"),
                expectedResponseBody.getJSONObject("data").get("id"));

        softAssert.assertEquals(responseJsonPath.get("data.employee_name"),
                expectedResponseBody.getJSONObject("data").get("employee_name"));

        softAssert.assertEquals(responseJsonPath.get("data.employee_salary"),
                expectedResponseBody.getJSONObject("data").get("employee_salary"));

        softAssert.assertEquals(responseJsonPath.get("data.employee_age"),
                expectedResponseBody.getJSONObject("data").get("employee_age"));

        softAssert.assertEquals(responseJsonPath.get("data.profile_image"),
                expectedResponseBody.getJSONObject("data").get("profile_image"));

        softAssert.assertAll();

        /*
           output:
           java.lang.AssertionError: The following asserts failed:
	       expected [Ali Can] but found [Ashton Cox],
	       expected [36] but found [66]
         */
    }
}
