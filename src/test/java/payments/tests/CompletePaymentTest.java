package payments.tests;

import POJOS.CancelPayment.CancelPaymentRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payments.data.CompletePaymentData;
import services.POSTService;

@Epic("Payments API")
@Feature("Complete Payment")
public class CompletePaymentTest extends CompletePaymentData {

    @Test(dataProvider = "completePaymentData")
    public void completePaymentById(String testName, String paymentId, CancelPaymentRequest request, int expectedStatus, String expectedPaymentStatus) {
        System.out.println("Running: " + testName);

        String endpoint = "/payments/" + paymentId + "/complete";

        Response res = POSTService.create(endpoint, request, expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200) {
            String status = res.jsonPath().getString("payment.status");
            Assert.assertEquals(status, expectedPaymentStatus, "Payment status mismatch!");
            System.out.println(testName + "Payment completed successfully. Status: " + status);
        } else {
            String errorMessage = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMessage.contains("invalid for the requested operation"),
                    "Unexpected error message for canceled payment completion.");
            System.out.println(testName + "Expected error response received: " + errorMessage);
        }

        System.out.println();
    }
}
