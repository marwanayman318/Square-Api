package refunds.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import services.GETService;
import refunds.data.GetRefundData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ListRefundsByIdTest extends GetRefundData {

    @Epic("Refund API")
    @Feature("List Refund By Id")
    @Test(dataProvider = "GetRefundData")
    public void getRefundById(String testName, String refundId, int expectedStatus, boolean expectValid) {
        System.out.println("Running: " + testName);

        String endpoint = "/refunds/" + refundId;

        Response res = GETService.list(endpoint, expectedStatus);
        res.prettyPrint();

        if (expectValid) {
            Assert.assertNotNull(res.jsonPath().getString("refund.id"), "Refund ID should not be null");
            Assert.assertNotNull(res.jsonPath().getString("refund.status"), "Refund status should not be null");
            Assert.assertNotNull(res.jsonPath().getString("refund.amount_money.amount"), "Refund amount should not be null");
            Assert.assertNotNull(res.jsonPath().getString("refund.payment_id"), "Payment ID should not be null");
            Assert.assertNotNull(res.jsonPath().getString("refund.created_at"), "created_at should not be null");
            Assert.assertNotNull(res.jsonPath().getString("refund.updated_at"), "updated_at should not be null");

            System.out.println(testName + "Refund retrieved successfully.");
        } else {
            String errorMsg = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMsg.contains("Could not find refund with id"),
                    "Expected error message not found in response.");
            System.out.println("✅ " + testName + " — Expected error occurred for invalid refund_id.");
        }

        System.out.println();
    }
}
