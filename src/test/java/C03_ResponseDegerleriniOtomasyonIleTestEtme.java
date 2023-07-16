import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class C03_ResponseDegerleriniOtomasyonIleTestEtme {

    @Test
    public void test02(){

        /*
            https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
            gonderdigimizde donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin Cowboy,
            ve status Line’in HTTP/1.1 200 OK
            oldugunu test edin
         */

        // 1- Request body ve end-point hazirlama

        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Expected data hazirlama

        // 3- Request gonderip, donen response'i kaydetme

        Response response = given().when().get(url);

        // 4- Assertion

        response
                .then()
                   .assertThat()
                      .statusCode(200)
                      .contentType("application/json; charset=utf-8")
                      .header("Server", "Cowboy")
                      .statusLine("HTTP/1.1 200 OK");
    }
}
