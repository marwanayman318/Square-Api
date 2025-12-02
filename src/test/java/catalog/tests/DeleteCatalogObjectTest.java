package catalog.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.DELETEService;

@Epic("Catalog API")
@Feature("Delete Catalog Object")
public class DeleteCatalogObjectTest extends catalog.Data.DeleteCatalogObjectData {

    @Test(dataProvider = "deleteCatalogObjectData")
    public void deleteCatalogObjectTest(String testName, String orderId, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/object/"+ orderId;

        Response res = DELETEService.delete(endpoint1, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"deleted_at\""),
                    "Expected key 'deleted_object_ids' is missing!");
        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");
            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }
}
