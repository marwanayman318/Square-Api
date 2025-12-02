package customer.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

@Epic("Customer API")
@Feature("Retrieve Customer By Id")
public class SingleCustomerTest extends customer.Data.SingleCustomerData {

    @Test(dataProvider = "singleCustomerData")
    public void retrieveCustomer(String testName, Object custid, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/" + custid;
        Response res = GETService.list(endpoint, expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customer"));
        }
        else {
            Assert.assertTrue(res.getBody().asString().contains("error"));
        }
    }
}
