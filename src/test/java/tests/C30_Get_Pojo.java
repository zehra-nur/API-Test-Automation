package tests;

import baseUrl.BaseUrlDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyData;
import pojos.PojoDummyResponseBody;

import static io.restassured.RestAssured.given;

public class C30_Get_Pojo extends BaseUrlDummy {

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
        specDummy.pathParams("pp1","employee","pp2",3);

        // 2- Expected data hazirlama

        PojoDummyData data = new PojoDummyData(3,
                                     "Ashton Cox",
                                      86000,
                                       66,
                                        "");

        PojoDummyResponseBody expectedResponseBody = new PojoDummyResponseBody("success",
                                                                              data,
                                                                     "Successfully! Record has been fetched.");


        // 3- Request gonderip, donen response'i kaydetme

        Response response = given().spec(specDummy)
                            .when()
                            .get("{pp1}/{pp2}");

        PojoDummyResponseBody responseBodyPojo = response.as(PojoDummyResponseBody.class);

        // 4- Assertion

        Assert.assertEquals(expectedResponseBody.getStatus(),responseBodyPojo.getStatus());
        Assert.assertEquals(expectedResponseBody.getMessage(),responseBodyPojo.getMessage());
        Assert.assertEquals(expectedResponseBody.getData().getId(),
                                responseBodyPojo.getData().getId());
        Assert.assertEquals(expectedResponseBody.getData().getEmployee_name(),
                                responseBodyPojo.getData().getEmployee_name());

        Assert.assertEquals(expectedResponseBody.getData().getEmployee_salary(),
                                responseBodyPojo.getData().getEmployee_salary());

        Assert.assertEquals(expectedResponseBody.getData().getEmployee_age(),
                                responseBodyPojo.getData().getEmployee_age());

        Assert.assertEquals(expectedResponseBody.getData().getProfile_image(),
                                responseBodyPojo.getData().getProfile_image());
    }
}
