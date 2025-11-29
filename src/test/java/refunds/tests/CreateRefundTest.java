package refunds.tests;

import POJOS.createPayment.PaymentRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import services.POSTService;
import refunds.data.CreateRefundData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateRefundTest extends CreateRefundData {

    @Epic("Refund API")
    @Feature("Create Refund")
    @Test(dataProvider = "createRefundData")
    public void createRefund(String testName, PaymentRequest request, int expectedStatus, String expectedRefundStatus) {
        System.out.println("Running: " + testName);

        String endpoint = "/refunds";

        Response res = POSTService.create(endpoint,request,expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200) {
            String actualStatus = res.jsonPath().getString("refund.status");
            Assert.assertNotNull(actualStatus, "Refund status should not be null");
            Assert.assertTrue(actualStatus.equalsIgnoreCase("COMPLETED") ||
                            actualStatus.equalsIgnoreCase("PENDING"),
                    "Refund status should be COMPLETED or PENDING");
            System.out.println(testName + "Refund created successfully. Status: " + actualStatus);
        } else {
            Assert.assertTrue(res.asString().contains("errors") || res.asString().contains("error"),
                    "Expected error response missing.");
            System.out.println(testName + "Expected error occurred. Status: " + expectedStatus);
        }

        System.out.println();
    }

}
