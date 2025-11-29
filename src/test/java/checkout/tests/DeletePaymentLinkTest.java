package checkout.tests;

import checkout.Data.DeletePaymentLinkData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.DELETEService;

public class DeletePaymentLinkTest extends DeletePaymentLinkData {

    @Epic("CheckOut API")
    @Feature("Delete Payment Link")
    @Test(dataProvider = "deletePaymentLinkData")
    public void deletePaymentLinkTest(String testName, String paymentLinkId, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName + " (ID=" + paymentLinkId + ")");

        String endpoint = "/online-checkout/payment-links/" + paymentLinkId;

        Response res = DELETEService.delete(endpoint, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertNotNull(res.jsonPath().getString("cancelled_order_id"),
                    "Expected cancelled_order_id but not found");
            System.out.println(testName + "Delete successful.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(error.contains("not found"),
                    "Expected 'not found' message but got: " + error);
            System.out.println(testName + "Correct error received: " + error);
        }

        System.out.println();
    }

}
