package Helper;

import POJOS.createPayment.PaymentRequest;
import POJOS.createPayment.money;
import io.restassured.response.Response;
import utilities.Config;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class CreatePaymentHelper {

    public static String createPayment(int amount, String currency, String sourceId) {

        try {
            PaymentRequest body = new PaymentRequest(UUID.randomUUID().toString(), sourceId, new money(amount, currency));

            Response res = given()
                    .baseUri(Config.BASE_URL)
                    .header("Authorization", Config.TOKEN)
                    .header("Content-Type", "application/json")
                    .body(body)
                    .when()
                    .post("/payments")
                    .then()
                    .extract()
                    .response();

            if (res.getStatusCode() != 200) {
                throw new RuntimeException(
                        "Failed to create payment. Received " + res.getStatusCode()
                                +res.getBody().asString()
                );
            }

            return res.jsonPath().getString("payment.id");

        } catch (Exception e) {
            throw new RuntimeException("Error creating Square payment", e);
        }
    }
}
