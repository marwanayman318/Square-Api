package catalog.Data;

import Helper.CreateItemHelper;
import org.testng.annotations.DataProvider;

public class DeleteCatalogObjectData {
    @DataProvider(name = "deleteCatalogObjectData")
    public static Object[][] deleteCatalogObject() {
        String orderId = CreateItemHelper.getAnyCatalogObjectId();

        return new Object[][]{
                {"DCO_TC_007", orderId, 200, true},
                {"DCO_TC_008", "ZAXFYNBOY7BNEM66FDW2QDEm", 404, false}
        };

    }
}
