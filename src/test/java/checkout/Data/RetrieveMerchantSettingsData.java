package checkout.Data;

import org.testng.annotations.DataProvider;
import utilities.Config;

public class RetrieveMerchantSettingsData {

    @DataProvider(name = "retrieveMerchantSettingsData")
    public Object[][] getData() {
        return new Object[][]{
                {
                        "RMS_TC_006 - Valid merchant settings retrieval",
                        200,
                        true,
                        Config.TOKEN
                },
                {
                        "RMS_TC_007 - Invalid token returns 401",
                        401,
                        false,
                        "INVALID_TOKEN"
                }
        };
    }
}

