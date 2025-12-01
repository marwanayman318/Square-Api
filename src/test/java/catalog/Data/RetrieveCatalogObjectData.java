package catalog.Data;

import Helper.CreateItemHelper;
import org.testng.annotations.DataProvider;

public class RetrieveCatalogObjectData {
    @DataProvider(name = "retrieveCatalogObjectData")
    public static Object[][] retrieveCatalogObject() {
        String orderId = CreateItemHelper.getAnyCatalogObjectId();

        return new Object[][]{
                {"RCO_TC_005", orderId, 200, true},
                {"RCO_TC_006", "ZAXFYNBOY7BNEM66FDW2QDEm", 404, false}
        };

    }
}
