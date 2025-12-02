package catalog.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Catalog API")
@Feature("Batch Retrieve Catalog Object")
public class BatchRetrieveCatalogObjectTest extends catalog.Data.BatchRetrieveCatalogObjectData {

    @Test(dataProvider = "batchRetrieveCatalogObjectData")
    public void batchRetrieveCatalogObjectTest(String testName, String body,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/batch-retrieve";

        Response res = POSTService.create(endpoint1, body ,expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"objects\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }
}
