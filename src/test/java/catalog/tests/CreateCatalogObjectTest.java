package catalog.tests;

import catalog.Data.CreateCatalogObjectData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;


public class CreateCatalogObjectTest extends CreateCatalogObjectData {

    public static String itemId;

    @Epic("Catalog API")
    @Feature("Create Catalog Object")
    @Test(dataProvider = "createCatalogObjectData")
    public void createCatalogObjectTest(String testName, Object body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/object";

        Response res = POSTService.create(endpoint1, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"catalog_object\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
        itemId = res.jsonPath().getString("catalog_object.id");
    }



}
