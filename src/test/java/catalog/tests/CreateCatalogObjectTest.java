package catalog.tests;

import catalog.Data.CreateCatalogObjectData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.DELETEService;
import services.GETService;
import services.POSTService;
import services.PUTService;

public class CatalogTest extends CreateCatalogObjectData {

    public static String itemId;

    @Epic("Catalog API")
    @Feature("Create Catalog Object")
    @Test(dataProvider = "createCatalogObjectData")
    public void createCatalogObjectTest(String testName, Object body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/object";

        Response res = POSTService.create(endpoint1, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"catalog_object\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
        itemId = res.jsonPath().getString("catalog_object.id");
    }

    ///////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Batch Upsert Catalog Object")
    @Test(dataProvider = "batchUpsertCatalogObjectData")
    public void batchUpsertCatalogObjectTest(String testName, Object body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/batch-upsert";

        Response res = POSTService.create(endpoint1, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"objects\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

    //////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Retrieve Catalog Object")
    @Test(dataProvider = "retrieveCatalogObjectData")
    public void retrieveCatalogObjectTest(String testName, String orderId, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/object/" + orderId;

        Response res = GETService.list(endpoint1, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"object\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

    /////////////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Delete Catalog Object")
    @Test(dataProvider = "deleteCatalogObjectData")
    public void deleteCatalogObjectTest(String testName, String orderId, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/object/"+ orderId;

        Response res = DELETEService.delete(endpoint1, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"deleted_at\""),
                    "Expected key 'deleted_object_ids' is missing!");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

    /////////////////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("List Catalog Object")
    @Test(dataProvider = "listCatalogObjectData")
    public void listCatalogObjectTest(String testName, String token,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/list";

        Response res = GETService.listWithToken(endpoint1, expectedStatus, token);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"objects\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }


    /////////////////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Batch Retrieve Catalog Object")
    @Test(dataProvider = "batchRetrieveCatalogObjectData")
    public void batchRetrieveCatalogObjectTest(String testName, String body,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/batch-retrieve";

        Response res = POSTService.create(endpoint1, body ,expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"objects\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }


    ///////////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Update Catalog Image")
    @Test(dataProvider = "updateCatalogImageData", dependsOnMethods = "createCatalogImageDataTest")
    public void updateCatalogImageTest(String testName ,String imageId ,String path ,String body,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/images/" + imageId;

        Response res = PUTService.updateWithImage(endpoint1, body, path, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"image\""),
                    "Expected 'image' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }


    ///////////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Search Catalog Object")
    @Test(dataProvider = "searchCatalogObjectsData")
    public void searchCatalogObjectsTest(String testName ,String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/search";

        Response res = POSTService.create(endpoint1, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"objects\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

    /////////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("List Catalog Info")
    @Test(dataProvider = "catalogInfoData")
    public void catalogInfoTest(String testName ,String token, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/info";

        Response res = GETService.listWithToken(endpoint1,expectedStatus,token);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"limits\""),
                    "Expected 'limits' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

    /////////////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Search Catalog Item")
    @Test(dataProvider = "searchCatalogItemData")
    public void searchCatalogItemTest(String testName ,String body, int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/search-catalog-items";

        Response res = POSTService.create(endpoint1,body,expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);
        boolean hasObject = res.getBody().asString().contains("\"items\"");
        boolean hasNoObject = res.getBody().asString().contains("{\"cursor\":\"\"}");

        if (expectSuccess) {
            Assert.assertTrue(hasObject || hasNoObject,
                    "Expected 'items' array missing from response");

        }else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

    /// ///////////////////////////////

    @Epic("Catalog API")
    @Feature("Batch Delete Catalog Object")
    @Test(dataProvider = "batchDeleteCatalogObjectsData")
    public void batchDeleteCatalogObjectsTest(String testName, String body,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/batch-delete";

        Response res = POSTService.create(endpoint1, body ,expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"deleted_object_ids\""),
                    "Expected 'object' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

/// /////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Create Catalog Image")
    @Test(dataProvider = "createCatalogImageData")
    public void createCatalogImageDataTest(String testName ,String path ,String body,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/images";

        Response res = POSTService.createWithImage(endpoint1, body, path, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"image\""),
                    "Expected 'image' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
}

    ///////////////////////////////////////////////////


    @Epic("Catalog API")
    @Feature("Update Item Modifier List")
    @Test(dataProvider = "updateItemModifierListData")
    public void updateItemModifierListTest(String testName ,String body ,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/update-item-modifier-lists";

        Response res = POSTService.create(endpoint1, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"updated_at\""),
                    "Expected 'image' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

    ///////////////////////////////////////////////////

    @Epic("Catalog API")
    @Feature("Update Item Modifier Taxes")
    @Test(dataProvider = "updateItemTaxesData")
    public void updateItemTaxesTest(String testName ,String body ,int expectedStatus, boolean expectSuccess) {

        System.out.println("Running: " + testName);

        String endpoint1 = "/catalog/update-item-taxes";

        Response res = POSTService.create(endpoint1, body, expectedStatus);
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), expectedStatus);

        if (expectSuccess) {
            Assert.assertTrue(res.getBody().asString().contains("\"updated_at\""),
                    "Expected 'image' field missing from response");

        } else {
            String detail = res.jsonPath().getString("errors[0].detail");
            Assert.assertNotNull(detail, "Error detail should not be null");

            System.out.println(testName + "Correct error returned: " + detail);
        }

        System.out.println();
    }

}
