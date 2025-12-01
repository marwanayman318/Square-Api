package catalog.Data;

import org.testng.annotations.DataProvider;
import utilities.Config;

public class CatalogInfoData {
    @DataProvider(name = "catalogInfoData")
    public static Object[][] catalogInfoData() {
        String token1 = Config.TOKEN;
        String token2 = "invalid-token";
        return new Object[][]{
                {"CI_TC_017", token1, 200, true},
                {"CI_TC_018", token2, 401, false}
        };
    }
}

