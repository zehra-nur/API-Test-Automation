import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C10_JsonPathKullanimi {
    /*
        {
          "firstname": "John",
          "lastname": "Doe"
          "age": 26,
          "address": {
            "streetAddress": "naist street",
            "city": "Nara",
            "postalCode": "630-0192"
          },
          "phoneNumber": [
            {
              "number": "0123-4567-8910",
              "type": "iPhone"
            },
            {
              "number": "0123-4567-8910",
              "type": "home"
            }
          ],
        }

     */

    @Test
    public void test(){

        JSONObject personalInfoJsonObje = new JSONObject();

        JSONObject addressJsonObje = new JSONObject();
        addressJsonObje.put("streetAddress","naist street");
        addressJsonObje.put("city","Nara");
        addressJsonObje.put("postalCode","630-0192");

        JSONArray phoneNumbers = new JSONArray();

        JSONObject iPhoneNumber = new JSONObject();
        iPhoneNumber.put("type","iPhone");
        iPhoneNumber.put("number","0123-4567-8910");

        JSONObject homeNumber = new JSONObject();
        homeNumber.put("type","home");
        homeNumber.put("number","0123-4567-8910");

        phoneNumbers.put(iPhoneNumber);
        phoneNumbers.put(homeNumber);

        personalInfoJsonObje.put("firstname","John");
        personalInfoJsonObje.put("lastname","Doe");
        personalInfoJsonObje.put("age",26);
        personalInfoJsonObje.put("address",addressJsonObje);
        personalInfoJsonObje.put("phoneNumber",phoneNumbers);

        System.out.println(personalInfoJsonObje);

        System.out.println("firstname : "+personalInfoJsonObje.get("firstname"));
        System.out.println("lastname : "+personalInfoJsonObje.get("lastname"));

        System.out.println("cadde : " +
                                    personalInfoJsonObje
                                            .getJSONObject("address")
                                            .get("streetAddress"));

        System.out.println("city : " +
                                    personalInfoJsonObje
                                            .getJSONObject("address")
                                            .get("city"));

        System.out.println("iPhone number : " +
                                    personalInfoJsonObje
                                            .getJSONArray("phoneNumber")
                                            .getJSONObject(0)
                                            .get("number"));

        // https://jsonpath.com/
    }
}
