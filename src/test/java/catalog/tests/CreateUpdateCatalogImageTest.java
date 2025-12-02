package catalog.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;
import services.PUTService;

@Epic("Catalog API")
@Feature("Create Catalog Image")
public class CreateUpdateCatalogImageTest extends catalog.Data.CreateCatalogImageData {

    @Test(dataProvider = "createCatalogImageData")
    public static void createCatalogImageTest(String testName , String path , String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/images";

        Response res = POSTService.createWithImage(endpoint1, body, path, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"image\""),
                    "Expected 'image' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");
            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

    @Test(dataProvider = "updateCatalogImageData", dependsOnMethods = "createCatalogImageTest")
    public void updateCatalogImageTest(String testName ,String imageId ,String path ,String body,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/images/" + imageId;

        Response res = PUTService.updateWithImage(endpoint1, body, path, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"image\""),
                    "Expected 'image' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");
            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

}
