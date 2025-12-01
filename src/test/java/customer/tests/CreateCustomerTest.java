package customer.tests;

import customer.Data.CreateCustomerData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.POSTService;

public class CreateCustomerTest extends CreateCustomerData {
    public static String custId;
    @Epic("Customer API")
    @Feature("Create Customer")
    @Test(dataProvider = "createCustomerData")
    public void createCustomerTests(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        custId = res.jsonPath().getString("customer.id");

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customer"));
            System.out.println("Created customer id: " + custId);
        } else {
            Assert.assertTrue(res.getBody().asString().contains("errors"));
            System.out.println("Expected error: " + res.jsonPath().getString("errors[0].detail"));
        }
    }
}
