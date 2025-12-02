package customer.tests;

import POJOS.listPayment.listPaymentRequests;
import customer.Data.listCustomersLimitData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

@Epic("Customer API")
@Feature(" List Customer with Limit")
public class ListCustomersLimitTest extends listCustomersLimitData {

    @Test(dataProvider = "listCustomersLimitZero")
    public void listCustomers_limitZero(String testName, int limit, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endPoint = "/customers";

        listPaymentRequests request = new listPaymentRequests();
        request.setLimit(limit);
        Response res = GETService.listWithParam(request, expectedStatus,endPoint);
        res.prettyPrint();

        String detail = res.jsonPath().getString("errors[0].detail");

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customers"));
            int returnedLimit = res.jsonPath().getInt("limit");
            Assert.assertEquals(returnedLimit, limit, "The returned limit does not match the requested limit.");
        } else {
            Assert.assertTrue(res.getBody().asString().contains("errors"));
            System.out.println("Expected error detail: " + detail);
        }
    }
}
