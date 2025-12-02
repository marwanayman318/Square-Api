package customer.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

@Epic("Customer API")
@Feature("List Customers")
public class ListCustomersTest extends customer.Data.ListCustomersData {

    @Test(dataProvider = "listCustomersData")
    public void listCustomers(String testName, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endPoint = "/customers";

        Response res = GETService.list(endPoint, expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customers"));
            System.out.println("Passed: " + testName);
        }
    }
}
