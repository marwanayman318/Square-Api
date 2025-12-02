package checkout.tests;

import Helper.PaymentLinkHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.PUTService;

@Epic("CheckOut API")
@Feature("Update Payment Link")
public class UpdatePaymentLinkTest extends checkout.Data.UpdatePaymentLinkData {

    @Test(dataProvider = "updatePaymentLinkData")
    public void updatePaymentLinkTest(String testName, String paymentLinkId, String bodyTemplate, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName + " (ID=" + paymentLinkId + ")");

        int version = PaymentLinkHelper.getVersion(paymentLinkId);

        String body = bodyTemplate.replace("{{version}}", String.valueOf(version));

        String endpoint = "/online-checkout/payment-links/" + paymentLinkId;

        Response res = PUTService.updatePayment(endpoint, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"payment_link\""),
                    "Expected payment_link object but not found");
            System.out.println(testName + "Successfully updated payment link.");
        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(detail.contains("Did not expect field to be set"),
                    "Expected 'Did not expect field to be set' but got: " + detail);
            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }
}
