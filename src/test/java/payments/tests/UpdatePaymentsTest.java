package payments.tests;

import POJOS.UpdatePayment.UpdatePayment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payments.data.UpdatePaymentsData;
import services.PUTService;

@Epic("Payments API")
@Feature("Update Payment")
public class UpdatePaymentsTest extends UpdatePaymentsData {

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
