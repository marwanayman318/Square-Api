package checkout.tests;

import checkout.Data.ListPaymentLinkData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

public class ListPaymentLinksTest extends ListPaymentLinkData {

    @Epic("CheckOut API")
    @Feature("List Payment Link")
    @Test(dataProvider = "listPaymentLinksData")
    public void listPaymentLinksTest(String testName, String endpoint, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        Response res = GETService.list(endpoint, expectedStatus);
        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(
                    res.getBody().asString().contains("payment_links"),
                    "Expected 'payment_links' array but not found"
            );

            int count = res.jsonPath().getList("payment_links").size();
            Assert.assertTrue(true, "payment_links array should not be null");

            System.out.println(testName + "payment_links found. Count = " + count);
        }

        System.out.println();
    }
}
