package refunds.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import services.GETService;
import refunds.data.ListRefundData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ListRefundsTest extends ListRefundData {

    @Epic("Refund API")
    @Feature("List Refund")
    @Test(dataProvider = "listRefundsData")
    public void listRefunds(String testName, String queryParams, int expectedStatus, boolean expectRefunds) {
        System.out.println("Running: " + testName);

        String endpoint = "/refunds"+ (queryParams != null ? queryParams : "");

        Response res = GETService.list(endpoint, expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200 && expectRefunds) {
            List<Object> refunds = res.jsonPath().getList("refunds");
            Assert.assertNotNull(refunds, "Refund list should not be null");
            Assert.assertFalse(refunds.isEmpty(), "Refund list should not be empty");

            // Check required fields
            String firstRefundStatus = res.jsonPath().getString("refunds[0].status");
            String firstRefundId = res.jsonPath().getString("refunds[0].id");
            String firstRefundCreatedAt = res.jsonPath().getString("refunds[0].created_at");
            Assert.assertNotNull(firstRefundId, "Refund ID should be present");
            Assert.assertNotNull(firstRefundStatus, "Refund status should be present");
            Assert.assertNotNull(firstRefundCreatedAt, "Refund created_at should be present");

            System.out.println(testName + "Received valid refund objects.");
        } else if (expectedStatus == 400) {
            Assert.assertTrue(res.asString().contains("errors"), "Error message should be present");
            System.out.println(testName + "Expected 400 error for invalid query param.");
        }

        System.out.println();
    }
}
