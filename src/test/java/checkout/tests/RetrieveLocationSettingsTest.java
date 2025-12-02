package checkout.tests;

import checkout.Data.LocationSettingsData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

@Epic("CheckOut API")
@Feature("Retrieve Location Settings ")
public class RetrieveLocationSettingsTest extends LocationSettingsData{

    @Test(dataProvider = "locationSettingsData")
    public void getLocationSettingsTest(String testName, String locationId, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName + " (locationId=" + locationId + ")");

        String endpoint = "/online-checkout/location-settings/" +locationId;

        Response res = GETService.list(endpoint, expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200) {
            Assert.assertTrue(res.getBody().asString().contains("location_settings"),
                    "Expected payment object in response but not found");
            System.out.println(testName + "location_settings present.");
        } else {
            String errorMessage = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMessage.toLowerCase().contains("does not have a location"),
                    "Expected location-not-found error message");
            System.out.println(testName + "error received for invalid location.");
        }

        System.out.println();
    }
}
