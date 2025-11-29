package customer.tests;

import customer.Data.CustomerData;
import POJOS.listPayment.listPaymentRequests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

public class ListCustomersTest extends CustomerData {

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

        @Test(dataProvider = "listCustomersLimitZero")
        public void listCustomers_limitZero(String testName, int limit, int expectedStatus, boolean expectSuccess) {
            System.out.println("Running: " + testName);

            String endPoint = "/customers";

            listPaymentRequests request = new listPaymentRequests();
            request.setLimit(limit);
            Response res = GETService.listWithParam(request, expectedStatus,endPoint);
            res.prettyPrint();

            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(detail != null && detail.toLowerCase().contains("must not be less"),
                    "Expected validation error for limit");
        }
}

