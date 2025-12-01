package payments.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payments.data.GetPaymentsData;
import services.GETService;

public class GetPaymentsTest extends GetPaymentsData {
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
}
