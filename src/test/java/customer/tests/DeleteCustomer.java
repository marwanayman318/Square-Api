package customer.tests;

import customer.Data.CustomerData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.DELETEService;


public class DeleteCustomer extends CustomerData {

    @Epic("Customer API")
    @Feature("Delete Customer")
    @Test(dataProvider = "deleteCustomerData")
    public void deleteCustomer(String testName, Object custid, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/" + custid;
        Response res = DELETEService.delete(endpoint,expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }
}
