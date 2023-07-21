package test_datalari;

import org.json.JSONObject;

public class TestDataHerokuapp {
    /*       Request body                                     Response Body
         {                                                {
            "firstname" : "Ahmet",                           "bookingid": 24,
            "lastname" : “Bulut",                            "booking": {
            "totalprice" : 500,                                 "firstname": "Ahmet",
            "depositpaid" : false,                              "lastname": "Bulut",
            "bookingdates" : {                                  "totalprice": 500,
               "checkin" : "2021-06-01",                        "depositpaid": false,
               "checkout" : "2021-06-10"                        "bookingdates": {
            },                                                       "checkin": "2021-06-01",
            "additionalneeds" : "wi-fi"                              "checkout": "2021-06-10"
         }                                                      }
                                                                "additionalneeds": "wi-fi"
                                                             }
                                                           }
     */
    public static JSONObject jsonRequestBodyOlustur(){

        JSONObject requestBody = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin" , "2021-06-01");
        bookingdates.put("checkout" , "2021-06-10");

        requestBody.put("firstname" , "Ahmet");
        requestBody.put("lastname" , "Bulut");
        requestBody.put("totalprice" , 500);
        requestBody.put("depositpaid" , false);
        requestBody.put("bookingdates" , bookingdates);
        requestBody.put("additionalneeds" , "wi-fi");

        return requestBody;
    }

    public static JSONObject jsonResponseBodyOlustur(){

        JSONObject responseBody = new JSONObject();
        JSONObject bookingBody = jsonRequestBodyOlustur();

        responseBody.put("bookingid", 24);
        responseBody.put("booking",bookingBody);

        return responseBody;
    }
}
