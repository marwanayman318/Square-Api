package payments.tests;

import POJOS.createPayment.PaymentRequest;
import POJOS.createPayment.money;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payments.data.PaymentsData;
import services.POSTService;
import java.util.UUID;

@Epic("Payments API")
@Feature("Create Payment")
public class PaymentsTest  extends PaymentsData {

    @Test(dataProvider = "PaymentsData")
    public void createPayment (int amount, String currency, String sourceId, boolean isSuccess, int expectedStatus) {

        String endpoint = "/payments";
        PaymentRequest request = new PaymentRequest(UUID.randomUUID().toString(), sourceId, new money(amount, currency),false);

        Response res = POSTService.create(endpoint,request, expectedStatus);

        res.prettyPrint();
        if (expectedStatus == 200) {
            Assert.assertTrue(res.getBody().asString().contains("payment"));
        }
        else {
            Assert.assertTrue(res.getBody().asString().contains("errors"));
        }

    }
}
