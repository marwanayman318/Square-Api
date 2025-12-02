package customer.tests;

import customer.Data.BulkUpdateData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Customer API")
@Feature("Bulk Update Customer")
public class BulkUpdateTest extends BulkUpdateData {

    @Test(dataProvider = "bulkUpdateData")
    public void bulkUpdate(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);


        String endpoint = "/customers/bulk-update";

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
