package customer.tests;

import customer.Data.BulkRetrieveData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

public class BulkRetrieveTest extends BulkRetrieveData {
    @Epic("Customer API")
    @Feature("Bulk Retrieve Customer")
    @Test(dataProvider = "bulkRetrieveData")
    public void bulkRetrieve(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/bulk-retrieve";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);

    }
}
