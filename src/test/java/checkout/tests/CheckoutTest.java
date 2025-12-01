package checkout.tests;

import checkout.Data.CreatePaymentLinkData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;


public class CheckoutTest extends CreatePaymentLinkData {

    @Epic("CheckOut API")
    @Feature("Create Payment Link")
    @Test(dataProvider = "createPaymentLinkData")
    public void createPaymentLinkTest(String testName, String endpoint, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(
                    res.getBody().asString().contains("payment_link"),
                    "Expected payment_link object in response"
            );
            System.out.println(testName + "Link created successfully.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(error, "Expected error message but none returned.");
            System.out.println(testName + "Correct error received: " + error);
        }

        System.out.println();
    }
}
