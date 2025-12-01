package payments.tests;

import POJOS.CancelPayment.CancelPaymentRequest;
import POJOS.UpdatePayment.UpdatePayment;
import POJOS.createPayment.PaymentRequest;
import POJOS.createPayment.money;
import POJOS.listPayment.listPaymentRequests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import payments.data.CancelPaymentByIdData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;
import services.POSTService;
import services.PUTService;
import utilities.Config;

import java.util.List;
import java.util.UUID;

public class PaymentIdTest extends CancelPaymentByIdData {

    @Epic("Payments API")
    @Feature("Cancel Payment By Id")
    @Test(dataProvider = "cancelPaymentByIdData")

    public void cancelPaymentById(String testName, String paymentId, CancelPaymentRequest request, int expectedStatus, String expectedPaymentStatus) {
        System.out.println("Running: " + testName);

        String endpoint = "/payments/"+paymentId+"/cancel";

        Response res = POSTService.create(endpoint, request, expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200) {
            String status = res.jsonPath().getString("payment.status");
            Assert.assertEquals(status, expectedPaymentStatus, "Payment status mismatch!");
            System.out.println("✅ " + testName + " — Payment canceled successfully. Status: " + status);
        } else {
            Assert.assertTrue(res.getBody().asString().contains("errors"), "Expected error not found!");
            String errorMessage = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMessage.contains("invalid for the requested operation"),
                    "Unexpected error message for completed payment cancellation.");
            System.out.println("✅ " + testName + " — Expected error response received: " + errorMessage);
        }

        System.out.println();
    }

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

    @Epic("Payments API")
    @Feature("Complete Payment")
    @Test(dataProvider = "completePaymentData")
    public void completePaymentById(String testName, String paymentId, CancelPaymentRequest request, int expectedStatus, String expectedPaymentStatus) {
        System.out.println("Running: " + testName);

        String endpoint = "/payments/" + paymentId + "/complete";

        Response res = POSTService.create(endpoint, request, expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200) {
            String status = res.jsonPath().getString("payment.status");
            Assert.assertEquals(status, expectedPaymentStatus, "Payment status mismatch!");
            System.out.println(testName + " — Payment completed successfully. Status: " + status);
        } else {
            Assert.assertTrue(res.getBody().asString().contains("errors"), "Expected error not found!");
            String errorMessage = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMessage.contains("invalid for the requested operation"),
                    "Unexpected error message for canceled payment completion.");
            System.out.println(testName + " — Expected error response received: " + errorMessage);
        }

        System.out.println();
    }

    @Epic("Payments API")
    @Feature("Create Payment")
    @Test(dataProvider = "PaymentsData")
    public void createPayment (int amount, String currency, String sourceId, boolean autoComplete, int expectedStatus) {

        String endpoint = "/payments";
        money money = new money(amount, currency);
        PaymentRequest request = new PaymentRequest(UUID.randomUUID().toString(), sourceId, new money(amount, currency));


        Response res = new POSTService().create(endpoint,request, expectedStatus);
        Assert.assertEquals(res.statusCode(), expectedStatus);

        res.prettyPrint();
        if (expectedStatus == 200) {
            Assert.assertTrue(res.getBody().asString().contains("payment"));
        }

    }

    @Epic("Payments API")
    @Feature("List Payment By Id")
    @Test(dataProvider = "getPaymentsData")
    public void getPaymentByIdTest(String testName, String paymentId, int expectedStatus, boolean isValid) {
        System.out.println("Running: " + testName);

        String endpoint = "/payments/"+ paymentId;

        Response res = GETService.list(endpoint, expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200) {
            Assert.assertTrue(res.getBody().asString().contains("payment"),
                    "Expected payment object in response but not found");
            System.out.println(testName + "Payment found successfully.");
        } else {
            String errorMessage = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMessage.toLowerCase().contains("could not find payment"),
                    "Unexpected or missing error message in response");
            System.out.println(testName + "Correct error message for invalid payment ID.");
        }

        System.out.println();
    }
    GETService service = new GETService();
    @Epic("Payments API")
    @Feature("List All Payment")
    @Test(dataProvider = "listPaymentsData")
    public void listPayments(String paramType, Object value, Object value2, int expectedStatus, String description) {
        System.out.println("Running: " + description);

        String endPoint = "/payments";

        listPaymentRequests request = new listPaymentRequests();
        request.setToken(paramType.equals("noPayments") ? Config.TOKEN2 : Config.TOKEN);

        switch (paramType) {
            case "valid":
            case "limit":
                request.setLimit((Integer) value);
                break;
            case "begin_time":
                request.setBeginTime((String) value);
                break;
            case "end_time":
                request.setEndTime((String) value);
                break;
            case "sort_order":
                request.setSortOrder((String) value);
                break;
            case "cursor":
                request.setCursor((String) value);
                request.setLimit((Integer) value2);
                break;
        }

        Response res = service.listWithParam(request, expectedStatus,endPoint);
        Assert.assertEquals(res.getStatusCode(), expectedStatus, "Unexpected status code");

        res.prettyPrint();


        if (expectedStatus == 200) {
            String body = res.getBody().asString().trim();
            if (paramType.equals("noPayments")) {

                List<Object> payments = res.jsonPath().getList("payments");
                int count = (payments != null) ? payments.size() : 0;
                Assert.assertEquals(count, 0, "Expected no payments for token2 (sandbox)");
            } else {

                Assert.assertTrue(body.contains("\"payments\"") || body.contains("payments"),
                        "Response body does not contain 'payments' key: " + body);
                List<Object> payments = res.jsonPath().getList("payments");
                Assert.assertNotNull(payments, "Expected 'payments' array in response but found null");
            }
        } else if (expectedStatus == 400) {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(error != null && error.toLowerCase().contains(paramType),
                    "Unexpected error message: " + error);
        }


        System.out.println(description + " Passed\n");
    }

    @Epic("Payments API")
    @Feature("Update Payment")
    @Test(dataProvider = "updatePaymentsData")
    public void updatePaymentTest(String testName, String paymentId, UpdatePayment request, int expectedStatus, boolean isSuccessExpected) {
        System.out.println("Running: " + testName);

        String endpoint = "/payments/" + paymentId;

        Response res = PUTService.updatePayment(endpoint, request, expectedStatus);
        res.prettyPrint();

        if (isSuccessExpected) {
            Assert.assertTrue(res.getBody().asString().contains("payment"),
                    "Expected payment object in response but not found");
            System.out.println(testName + "Payment updated successfully.");
        } else {
            String errorMessage = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMessage.toLowerCase().contains("invalid for the requested operation"),
                    "Unexpected or missing error message for invalid update.");
            System.out.println(testName + "Correct error message for invalid update attempt.");
        }

        System.out.println();

    }

}
