package customer.tests;

import customer.Data.CustomerData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

public class BulkUpdateCustomerTest extends CustomerData {

    @Epic("Customer API")
    @Feature("Bulk Update Customer")
    @Test(dataProvider = "bulkUpdateData")
    public void bulkUpdate(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);


        String endpoint = "/customers/bulk-update";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }
}
