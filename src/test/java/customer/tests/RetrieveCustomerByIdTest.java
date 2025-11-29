package customer.tests;

import customer.Data.CustomerData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

public class RetrieveCustomerByIdTest extends CustomerData {

    @Epic("Customer API")
    @Feature("Retrieve Customer By Id")
    @Test(dataProvider = "singleCustomerData")
    public void retrieveCustomer(String testName, Object custid, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/" + custid;
        Response res = GETService.list(endpoint, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }
}
