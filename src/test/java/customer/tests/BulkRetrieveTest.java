package customer.tests;

import customer.Data.BulkRetrieveData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Customer API")
@Feature("Bulk Retrieve Customer")
public class BulkRetrieveTest extends BulkRetrieveData {

    @Test(dataProvider = "bulkRetrieveData")
    public void bulkRetrieve(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/bulk-retrieve";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("responses"));
        }
        else {
            Assert.assertTrue(res.getBody().asString().contains("error"));
        }

    }
}
