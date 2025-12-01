package catalog.Data;

import Helper.CreateItemHelper;
import org.testng.annotations.DataProvider;

public class BatchRetrieveCatalogObjectData {
    @DataProvider(name = "batchRetrieveCatalogObjectData")
    public static Object[][] batchRetrieveCatalogObjectData() {
        String orderId1 = CreateItemHelper.getAnyCatalogObjectId();
        String orderId2 = CreateItemHelper.getAnyCatalogObjectId();
        String body1 = "{\n" +
                "  \"object_ids\": [\n" +
                "   \"" + orderId1 + "\",\n" +
                "    \"" + orderId2 + "\" \n" +
                "  ]\n" +
                "}";
        String body2 = "{ \"object_ids\": [] }";
        return new Object[][]{
                {"LC_TC_011", body1, 200, true},
                {"LC_TC_012", body2, 400, false}
        };

    }
}
