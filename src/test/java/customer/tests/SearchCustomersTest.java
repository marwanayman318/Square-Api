package customer.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Customer API")
@Feature("Search Customer")
public class SearchCustomersTest extends customer.Data.SearchCustomerData {

    @Test(dataProvider = "searchCustomerData")
    public void searchCustomer(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/search";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customers"));
        }
        else {
            Assert.assertTrue(res.getBody().asString().contains("error"));
        }
    }
}
