package checkout.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.PUTService;

@Epic("CheckOut API")
@Feature("Update Location Settings ")
public class UpdateLocationSettingsTest extends checkout.Data.UpdateLocationSettingsData {

    @Test(dataProvider = "updateLocationSettingsData")
    public void updateLocationSettingsTest(String testName, String locationId, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName + " (locationId=" + locationId + ")");

        String endpoint = "/online-checkout/location-settings/" + locationId;

        Response res = PUTService.updatePayment(endpoint, body,expectedStatus);
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("location_settings"),
                    "Expected location_settings object in response but not found");
            System.out.println(testName + " Update succeeded.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(error, "Expected error but none returned");
            System.out.println(testName + "Correct error received: " + error);
        }

        System.out.println();
    }
}
