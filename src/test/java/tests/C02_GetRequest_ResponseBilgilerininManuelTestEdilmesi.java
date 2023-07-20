package tests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_GetRequest_ResponseBilgilerininManuelTestEdilmesi {

    @Test
    public void test01(){
        /*
            https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
            gonderdigimizde donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin Cowboy,
            ve status Line’in HTTP/1.1 200 OK
            ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
         */

        // 1- Request body ve end-point hazirlama
        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Expected data hazirlama

        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().when().get(url);

        System.out.println("Status Code : " + response.getStatusCode() +
                           "\nContent Type : " + response.getContentType() +
                           "\nServer Header Degeri : " + response.getHeader("Server") +
                           "\nStatus Line : " + response.getStatusLine() +
                           "\nResponse Suresi : " + response.getTime());

        // 4- Assertion
    }
}
