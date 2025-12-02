package catalog.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Catalog API")
@Feature("Search Catalog Item")
public class SearchCatalogItemTest extends catalog.Data.SearchCatalogItemData {

    @Test(dataProvider = "searchCatalogItemData")
    public void searchCatalogItemTest(String testName ,String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/search-catalog-items";

        Response res = POSTService.create(endpoint1,body,expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        boolean hasObject = res.getBody().asString().contains("\"items\"");
        boolean hasNoObject = res.getBody().asString().contains("{\"cursor\":\"\"}");

        if (expectSuccess) {
            Assert.assertTrue(hasObject || hasNoObject,
                    "Expected 'items' array missing from response");

        }else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }
}
