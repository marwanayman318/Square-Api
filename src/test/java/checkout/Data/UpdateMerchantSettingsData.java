package checkout.Data;

import org.testng.annotations.DataProvider;

public class UpdateMerchantSettingsData {

    @DataProvider(name = "updateMerchantSettingsData")
    public Object[][] getData() {
        return new Object[][] {

                // VALID UPDATE
                {
                        "UMS_TC_008 - Update merchant settings valid",
                        "{ \"merchant_settings\": { \"branding\": { \"button_color\": \"#1122FF\" } } }",
                        200,
                        true
                },
                // INVALID BODY
                {
                        "UMS_TC_010 - Invalid body structure",
                        "{ }",
                        400,
                        false
                }
        };
    }
}
