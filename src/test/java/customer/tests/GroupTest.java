package customer.tests;

import customer.Data.GroupData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.DELETEService;
import services.PUTService;

@Epic("Customer API")
@Feature(" Create Customer Group")
public class GroupTest extends GroupData {

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
        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("{}"));
        }
        else {
            Assert.assertTrue(res.getBody().asString().contains("error"));
        }
    }}
