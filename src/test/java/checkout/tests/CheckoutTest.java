package checkout.tests;

import Helper.PaymentLinkHelper;
import checkout.Data.CheckoutData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.DELETEService;
import services.GETService;
import services.POSTService;
import services.PUTService;

public class CheckoutTest extends CheckoutData {

    @Epic("CheckOut API")
    @Feature("Create Payment Link")
    @Test(dataProvider = "createPaymentLinkData")
    public void createPaymentLinkTest(String testName, String endpoint, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        Response res = POSTService.create(endpoint, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(
                    res.getBody().asString().contains("payment_link"),
                    "Expected payment_link object in response"
            );
            System.out.println(testName + "Link created successfully.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(error, "Expected error message but none returned.");
            System.out.println(testName + "Correct error received: " + error);
        }

        System.out.println();
    }


    //Delete Payment Link Test
    @Epic("CheckOut API")
    @Feature("Delete Payment Link")
    @Test(dataProvider = "deletePaymentLinkData")
    public void deletePaymentLinkTest(String testName, String paymentLinkId, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName + " (ID=" + paymentLinkId + ")");

        String endpoint = "/online-checkout/payment-links/" + paymentLinkId;

        Response res = DELETEService.delete(endpoint, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertNotNull(res.jsonPath().getString("cancelled_order_id"),
                    "Expected cancelled_order_id but not found");
            System.out.println(testName + "Delete successful.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(error.contains("not found"),
                    "Expected 'not found' message but got: " + error);
            System.out.println(testName + "Correct error received: " + error);
        }

        System.out.println();
    }

    //listPaymentLinksTest
    @Epic("CheckOut API")
    @Feature("List Payment Link")
    @Test(dataProvider = "listPaymentLinksData")
    public void listPaymentLinksTest(String testName, String endpoint, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        Response res = GETService.list(endpoint, expectedStatus);
        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(
                    res.getBody().asString().contains("payment_links"),
                    "Expected 'payment_links' array but not found"
            );

            int count = res.jsonPath().getList("payment_links").size();
            Assert.assertTrue(true, "payment_links array should not be null");

            System.out.println(testName + "payment_links found. Count = " + count);
        }

        System.out.println();
    }

    //retrieveLocationSettingsTest
    @Epic("CheckOut API")
    @Feature("Retrieve Location Settings ")
    @Test(dataProvider = "locationSettingsData")
    public void getLocationSettingsTest(String testName, String locationId, int expectedStatus, boolean expectSuccess) {
        System.out.println("Running: " + testName + " (locationId=" + locationId + ")");

        String endpoint = "/online-checkout/location-settings/" +locationId;

        Response res = GETService.list(endpoint, expectedStatus);
        res.prettyPrint();

        if (expectedStatus == 200) {
            Assert.assertTrue(res.getBody().asString().contains("location_settings"),
                    "Expected payment object in response but not found");
            System.out.println(testName + "location_settings present.");
        } else {
            String errorMessage = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(errorMessage.toLowerCase().contains("does not have a location"),
                    "Expected location-not-found error message");
            System.out.println(testName + "error received for invalid location.");
        }

        System.out.println();
    }

    //retrieve merchant settings test
    @Epic("CheckOut API")
    @Feature("Retrieve Merchant Settings ")
    @Test(dataProvider = "retrieveMerchantSettingsData")
    public void retrieveMerchantSettingsTest(String testName, int expectedStatus, boolean expectSuccess, String tokenType) {

        System.out.println("Running: " + testName);

        String endpoint = "/online-checkout/merchant-settings";

        Response res = GETService.listWithToken(endpoint, expectedStatus,tokenType);
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("merchant_settings"),
                    "Expected merchant_settings in response but not found");
            System.out.println(testName + "Merchant settings retrieved successfully.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(error, "Expected error but none was returned");
            System.out.println(testName + "Error received: " + error);
        }

        System.out.println();
    }

    //retrievePaymentLinkTest
    @Epic("CheckOut API")
    @Feature("Retrieve Payment Link")
    @Test(dataProvider = "retrievePaymentLinkData")
    public void retrievePaymentLinkTest(String testName, String paymentLinkId, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName + " (ID=" + paymentLinkId + ")");

        String endpoint = "/online-checkout/payment-links/" + paymentLinkId;

        Response res = GETService.list(endpoint,expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {

            Assert.assertTrue(res.getBody().asString().contains("payment_link"),
                    "Expected payment_link object but not found");

            System.out.println(testName + "Payment link successfully retrieved.");

        } else {

            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(error.contains("not found"),
                    "Expected 'not found' error, received: " + error);

            System.out.println(testName + "Correct error returned: " + error);
        }

        System.out.println();
    }

    //update location settings test
    @Epic("CheckOut API")
    @Feature("Update Location Settings ")
    @Test(dataProvider = "updateLocationSettingsData")
    public void updateLocationSettingsTest(String testName, String locationId, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName + " (locationId=" + locationId + ")");

        String endpoint = "/online-checkout/location-settings/" + locationId;

        Response res = PUTService.updatePayment(endpoint, body,expectedStatus);
        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("location_settings"),
                    "Expected location_settings object in response but not found");
            System.out.println(testName + " Update succeeded.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(error, "Expected error but none returned");
            System.out.println(testName + "Correct error received: " + error);
        }

        System.out.println();
    }

    //update merchant settings test
    @Epic("CheckOut API")
    @Feature("Update Merchant Settings ")
    @Test(dataProvider = "updateMerchantSettingsData")
    public void updateMerchantSettingsTest(String testName, String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint = "/online-checkout/merchant-settings";

        Response res = PUTService.updatePayment(endpoint, body, expectedStatus);

        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        res.prettyPrint();

        if (expectSuccess) {
            Assert.assertTrue(
                    res.getBody().asString().contains("merchant_settings"),
                    "Expected merchant_settings object but not found"
            );
            System.out.println(testName + "Update succeeded.");
        } else {
            String error = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(error, "Expected error but none returned");
            System.out.println(testName + "Correct error: " + error);
        }

        System.out.println();
    }

    //update payment link test
    @Epic("CheckOut API")
    @Feature("Update Payment Link")
    @Test(dataProvider = "updatePaymentLinkData")
    public void updatePaymentLinkTest(String testName, String paymentLinkId, String bodyTemplate, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName + " (ID=" + paymentLinkId + ")");

        //Fetch the version HERE, inside the test
        int version = PaymentLinkHelper.getVersion(paymentLinkId);

        //Replace {{version}} placeholder inside JSON body
        String body = bodyTemplate.replace("{{version}}", String.valueOf(version));

        String endpoint = "/online-checkout/payment-links/" + paymentLinkId;

        Response res = PUTService.updatePayment(endpoint, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"payment_link\""),
                    "Expected payment_link object but not found");
            System.out.println(testName + " → Successfully updated payment link.");
        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertTrue(detail.contains("Did not expect field to be set"),
                    "Expected 'Did not expect field to be set' but got: " + detail);
            System.out.println(testName + " → Correct error returned: " + detail);
        }

        System.out.println();
    }
}
