package customer.tests;

import POJOS.listPayment.listPaymentRequests;
import customer.Data.listCustomersLimitData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GETService;

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
        Assert.assertTrue(detail != null && detail.toLowerCase().contains("must not be less"),
                "Expected validation error for limit");
    }
}
