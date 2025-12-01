package checkout.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.PUTService;

public class UpdateMerchantSettingsTest extends checkout.Data.UpdateMerchantSettingsData {
    //UpdateMerchantSettingsTest
    @Epic("CheckOut API")
    @Feature("Update Merchant Settings ")
    @Test(dataProvider = "updateMerchantSettingsData")
    public void updateMerchantSettingsTest(String testName, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint = "/online-checkout/merchant-settings";

        Response res = PUTService.updatePayment(endpoint, body, expectedStatus);

        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(
                    res.getBody().asString().contains("merchant_settings"),
                    "Expected merchant_settings object but not found"
            );
            System.out.println(testName + "Update succeeded.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(error, "Expected error but none returned");
            System.out.println(testName + "Correct error: " + error);
        }

        System.out.println();
    }
}
