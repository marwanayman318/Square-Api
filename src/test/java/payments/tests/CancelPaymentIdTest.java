package payments.tests;

import POJOS.CancelPayment.CancelPaymentRequest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import payments.data.CancelPaymentByIdData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Payments API")
@Feature("Cancel Payment By Id")
public class CancelPaymentIdTest extends CancelPaymentByIdData {

    @Test(dataProvider = "CancelPaymentByIdData")
    public void cancelPaymentById(String testName, String paymentId, CancelPaymentRequest request, int expectedStatus, String expectedPaymentStatus) {
        System.out.println("Running: " + testName);

        String endpoint = "/payments/"+paymentId+"/cancel";

        Response res = POSTService.create(endpoint, request, expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200) {
            String status = res.jsonPath().getString("payment.status");
            Assert.assertEquals(status, expectedPaymentStatus, "Payment status mismatch!");
            System.out.println(testName + "Payment canceled successfully. Status: " + status);
        } else {
            String errorMessage = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMessage.contains("invalid for the requested operation"),
                    "Unexpected error message for completed payment cancellation.");
            System.out.println(testName + "Expected error response received: " + errorMessage);
        }

        System.out.println();
    }
}
