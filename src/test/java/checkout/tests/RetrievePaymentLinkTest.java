package checkout.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

@Epic("CheckOut API")
@Feature("Retrieve Payment Link")
public class RetrievePaymentLinkTest extends checkout.Data.RetrievePaymentLinkData {

    @Test(dataProvider = "retrievePaymentLinkData")
    public void retrievePaymentLinkTest(String testName, String paymentLinkId, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName + " (ID=" + paymentLinkId + ")");

        String endpoint = "/online-checkout/payment-links/" + paymentLinkId;

        Response res = GETService.list(endpoint,expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {

            Assert.assertTrue(res.getBody().asString().contains("payment_link"),
                    "Expected payment_link object but not found");
            System.out.println(testName + "Payment link successfully retrieved.");

        } else {

            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(error.contains("not found"),
                    "Expected 'not found' error, received: " + error);

            System.out.println(testName + "Correct error returned: " + error);
        }

        System.out.println();
    }
}
