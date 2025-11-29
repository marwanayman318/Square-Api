package Helper;

import io.restassured.response.Response;
import utilities.Config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class PaymentLinkHelper {

    public static int getVersion(String paymentLinkId) {

        Response res = given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", Config.TOKEN)
                .when()
                .get("/online-checkout/payment-links/" + paymentLinkId)
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .extract()
                .response();

        if (res.getStatusCode() == 404) {
            throw new RuntimeException(
                    "Payment link NOT FOUND: " + paymentLinkId +
                            "Create a fresh payment link before running this test."
            );
        }

        return res.jsonPath().getInt("payment_link.version");
    }

    public static String createPaymentLink() {

        String body =
                "{\n" +
                        "  \"order\": {\n" +
                        "    \"location_id\": \"" + Config.LOCATION_ID + "\",\n" +
                        "    \"line_items\": [\n" +
                        "      {\n" +
                        "        \"name\": \"Test Item\",\n" +
                        "        \"quantity\": \"1\",\n" +
                        "        \"base_price_money\": {\n" +
                        "          \"amount\": 100,\n" +
                        "          \"currency\": \"USD\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}";

        Response res = given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", Config.TOKEN)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/online-checkout/payment-links")
                .then()
                .statusCode(200)
                .extract().response();

        return res.jsonPath().getString("payment_link.id");
    }




}
