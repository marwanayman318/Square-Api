package catalog.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Catalog API")
@Feature("Update Item Modifier List")
public class UpdateItemModifierListTest extends catalog.Data.UpdateItemModifierListData {

    @Test(dataProvider = "updateItemModifierListData")
    public void updateItemModifierListTest(String testName ,String body ,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/update-item-modifier-lists";

        Response res = POSTService.create(endpoint1, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"updated_at\""),
                    "Expected 'image' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }
}
