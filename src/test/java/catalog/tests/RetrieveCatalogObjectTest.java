package catalog.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

public class RetrieveCatalogObjectTest extends catalog.Data.RetrieveCatalogObjectData {
    @Epic("Catalog API")
    @Feature("Retrieve Catalog Object")
    @Test(dataProvider = "retrieveCatalogObjectData")
    public void retrieveCatalogObjectTest(String testName, String orderId, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/object/" + orderId;

        Response res = GETService.list(endpoint1, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"object\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }
}
