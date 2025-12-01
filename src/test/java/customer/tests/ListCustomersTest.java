package customer.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

public class ListCustomersTest extends customer.Data.ListCustomersData {
    @Epic("Customer API")
    @Feature("List Customers")
    @Test(dataProvider = "listCustomersData")
    public void listCustomers(String testName, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endPoint = "/customers";

        Response res = GETService.list(endPoint, expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customers") || res.jsonPath().getList("customers") != null,
                    "customers array expected");
            System.out.println("Passed: " + testName);
        }
    }
}
