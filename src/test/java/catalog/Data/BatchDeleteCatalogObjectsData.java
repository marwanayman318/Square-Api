package catalog.Data;

import Helper.CreateItemHelper;
import org.testng.annotations.DataProvider;

public class BatchDeleteCatalogObjectsData {
    @DataProvider(name = "batchDeleteCatalogObjectsData")
    public static Object[][] batchDeleteCatalogObjectsData() {
        String objectId1 = CreateItemHelper.getAnyCatalogObjectId();
        String objectId2 = CreateItemHelper.getAnyCatalogObjectId();
        String body1 = "{\n " +
                " \"object_ids\": [ \"" + objectId1 + "\" ,\n" +
                " \"" + objectId2 + "\" ] \n" +
                "}";
        String body2 = "{ \"object_ids\": [] }";
        return new Object[][]{
                {"BDCO_TC_021", body1, 200, true},
                {"BDCO_TC_022", body2, 400, false}
        };
    }
}
