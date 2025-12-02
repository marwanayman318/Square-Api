package customer.tests;

import customer.Data.BulkDeleteData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Customer API")
@Feature("Bulk Delete Customer")
public class BulkDeleteTest extends BulkDeleteData {

    @Test(dataProvider = "bulkDeleteData")
    public void bulkDelete(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/bulk-delete";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("responses"));
        }
        else {
            Assert.assertTrue(res.getBody().asString().contains("error"));
        }

    }
}
