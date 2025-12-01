package payments.tests;

import POJOS.CancelPayment.CancelPaymentRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payments.data.CancelPaymentData;
import services.POSTService;


public class CancelPaymentTest extends CancelPaymentData {
    @Epic("Payments API")
    @Feature("Cancel Payment")
    @Test(dataProvider = "CancelPaymentData")
    public void CancelPaymentTests (String testName, CancelPaymentRequest request, int expectedStatus, String expectedPaymentStatus) {
        System.out.println("Running: " + testName);

        String endpoint = "/payments/cancel";

        Response res = POSTService.create(endpoint, request, expectedStatus);

        res.prettyPrint();

        if (expectedStatus == 200) {
            String status = res.jsonPath().getString("payment.status");
            Assert.assertEquals(status, expectedPaymentStatus, "Payment status mismatch!");
            System.out.println(testName + "Payment canceled successfully. Status: " + status);
        } else {
            Assert.assertTrue(res.getBody().asString().contains("errors"), "Expected error not found!");
            System.out.println(testName + "Expected error response received.");
        }

        System.out.println();
    }

}
