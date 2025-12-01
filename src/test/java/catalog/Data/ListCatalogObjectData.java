package catalog.Data;

import org.testng.annotations.DataProvider;
import utilities.Config;

public class ListCatalogObjectData {
    @DataProvider(name = "listCatalogObjectData")
    public static Object[][] listCatalogObject() {
        String token1 = Config.TOKEN;
        String token2 = "invalid-token";
        return new Object[][]{
                {"LC_TC_009", token1, 200, true},
                {"LC_TC_010", token2, 401, false}
        };
    }
}
