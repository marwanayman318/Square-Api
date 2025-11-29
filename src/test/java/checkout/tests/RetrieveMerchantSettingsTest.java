package checkout.tests;

import checkout.Data.RetrieveMerchantSettingsData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

public class RetrieveMerchantSettingsTest extends RetrieveMerchantSettingsData {

    @Epic("CheckOut API")
    @Feature("Retrieve Merchant Settings ")
    @Test(dataProvider = "retrieveMerchantSettingsData")
    public void retrieveMerchantSettingsTest(String testName, int expectedStatus, boolean expectSuccess, String tokenType) {

        System.out.println("Running: " + testName);

        String endpoint = "/online-checkout/merchant-settings";

        Response res = GETService.listWithToken(endpoint, expectedStatus,tokenType);
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("merchant_settings"),
                    "Expected merchant_settings in response but not found");
            System.out.println(testName + "Merchant settings retrieved successfully.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(error, "Expected error but none was returned");
            System.out.println(testName + "Error received: " + error);
        }

        System.out.println();
    }
}
