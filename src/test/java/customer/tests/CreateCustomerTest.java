package customer.tests;

import customer.Data.CreateCustomerData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

@Epic("Customer API")
@Feature("Create Customer")
public class CreateCustomerTest extends CreateCustomerData {

    @Test(dataProvider = "createCustomerData")
    public void createCustomerTests(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customer"));
        } else {
            Assert.assertTrue(res.getBody().asString().contains("errors"));
            System.out.println("Expected error: " + res.jsonPath().getString("errors[0].detail"));
        }
    }
}
