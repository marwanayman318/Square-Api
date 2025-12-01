package refunds.tests;

import POJOS.createPayment.PaymentRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import services.GETService;
import services.POSTService;
import refunds.data.createRefundData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RefundTest extends createRefundData {

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

    @Epic("Refund API")
    @Feature("List Refund By Id")
    @Test(dataProvider = "getRefundData")
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
