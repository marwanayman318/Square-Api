package customer.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.PUTService;

public class UpdateCustomerTest extends customer.Data.UpdateCustomerData {
    @Epic("Customer API")
    @Feature("Update Customer")
    @Test(dataProvider = "updateCustomerData")
    public void updateCustomer(String testName, String custid, Object body ,int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/" + custid;
        Response res = PUTService.updatePayment(endpoint, body,expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }
}
