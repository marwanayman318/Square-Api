package customer.tests;

import POJOS.listPayment.listPaymentRequests;
import customer.Data.CustomerData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.DELETEService;
import services.GETService;
import services.POSTService;
import services.PUTService;

public class CustomerTest extends CustomerData {

    @Epic("Customer API")
    @Feature("Bulk Create Customer")
    @Test(dataProvider = "bulkCreateData")
    public void bulkCreate(String testName, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint = "/customers/bulk-create";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("customer") || res.jsonPath().getMap("customer") != null);
        }
    }

    @Epic("Customer API")
    @Feature("Bulk Delete Customer")
    @Test(dataProvider = "bulkDeleteData")
    public void bulkDelete(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);


        String endpoint = "/customers/bulk-delete";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }

    @Epic("Customer API")
    @Feature("Bulk Retrieve Customer")
    @Test(dataProvider = "bulkRetrieveData")
    public void bulkRetrieve(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/bulk-retrieve";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);

    }

    @Epic("Customer API")
    @Feature("Bulk Update Customer")
    @Test(dataProvider = "bulkUpdateData")
    public void bulkUpdate(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);


        String endpoint = "/customers/bulk-update";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }
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

    @Epic("Customer API")
    @Feature(" Create Customer Group")
    @Test(dataProvider = "groupData")
    public void groupOperations(String testName, String custid, String groupId, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/" + custid + "/groups/" + groupId;

        Response res;
        if (testName.toLowerCase().contains("add")) {
            Object body= "{}";
            res = PUTService.updatePayment(endpoint, body,expectedStatus);
        } else {
            res = DELETEService.delete(endpoint,expectedStatus);
        }
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }

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

    @Epic("Customer API")
    @Feature("Retrieve Customer By Id")
    @Test(dataProvider = "singleCustomerData")
    public void retrieveCustomer(String testName, Object custid, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/" + custid;
        Response res = GETService.list(endpoint, expectedStatus);
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }

    @Epic("Customer API")
    @Feature("Search Customer")
    @Test(dataProvider = "searchCustomerData")
    public void searchCustomer(String testName, String body, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName);

        String endpoint = "/customers/search";

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);
    }

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
