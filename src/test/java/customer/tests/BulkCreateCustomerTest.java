package customer.tests;

import customer.Data.CustomerData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

public class BulkCreateCustomerTest extends CustomerData {

    @Epic("Customer API")
    @Feature("Bulk Create Customer")
    @Test(dataProvider = "bulkCreateData")
    public void bulkCreate(String testName, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint = "/customers/bulk-create";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customer") || res.jsonPath().getMap("customer") != null);
        }
    }
}
