package payments.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import payments.data.CreatePaymentsDataProvider;
import services.POSTService;
import io.restassured.response.Response;
import POJOS.createPayment.PaymentRequest;
import POJOS.createPayment.money;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class CreatePaymentTest extends CreatePaymentsDataProvider {

    @Epic("Payments API")
    @Feature("Create Payment")
    @Test(dataProvider = "PaymentsData")
    public void createPayment (int amount, String currency, String sourceId, boolean autoComplete, int expectedStatus) {

    String endpoint = "/payments";
    money money = new money(amount, currency);
    PaymentRequest request = new PaymentRequest(UUID.randomUUID().toString(), sourceId, new money(amount, currency));


    Response res = new POSTService().create(endpoint,request, expectedStatus);
    Assert.assertEquals(res.statusCode(), expectedStatus);

    res.prettyPrint();
    if (expectedStatus == 200) {
        Assert.assertTrue(res.getBody().asString().contains("payment"));
    }

    }

}
