package catalog.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

@Epic("Catalog API")
@Feature("List Catalog Object")
public class ListCatalogObjectTest extends catalog.Data.ListCatalogObjectData {

    @Test(dataProvider = "listCatalogObjectData")
    public void listCatalogObjectTest(String testName, String token,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/list";

        Response res = GETService.listWithToken(endpoint1, expectedStatus, token);
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
