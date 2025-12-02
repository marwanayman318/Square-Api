package customer.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Customer API")
@Feature("Bulk Create Customer")
public class BulkCreateTest extends customer.Data.BulkCreateData {

    @Test(dataProvider = "bulkCreateData")
    public void bulkCreate(String testName, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint = "/customers/bulk-create";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customer"));
        }
        else {
            Assert.assertTrue(res.getBody().asString().contains("error"));
        }
    }
}
