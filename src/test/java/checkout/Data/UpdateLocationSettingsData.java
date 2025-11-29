package checkout.Data;

import org.testng.annotations.DataProvider;

public class UpdateLocationSettingsData {

    @DataProvider(name = "updateLocationSettingsData")
    public Object[][] updateLocationSettingsData() {
        return new Object[][]{
                {
                        "Update branding settings (valid)",
                        "L7NA82DE840VB",
                        "{ \"location_settings\": { \"branding\": { \"button_color\": \"#00b23b\", \"button_shape\": \"ROUNDED\" } } }",
                        200,
                        true
                },
                {
                        "Update tipping settings (valid)",
                        "L7NA82DE840VB",
                        "{ \"location_settings\": { \"tipping\": { \"smart_tipping_enabled\": false, \"percentages\": [10, 15, 20] } } }",
                        200,
                        true
                },
                {
                        "Update_TC_005",
                        "L7NA82DE840VB",
                        "{ \"location_settings\": { \"branding\": { \"button_colour\": \"blue\" } } }",
                        400,
                        false
                }
        };
    }
}
