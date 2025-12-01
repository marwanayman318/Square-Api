package checkout.Data;

import org.testng.annotations.DataProvider;

public class LocationSettingsData {
    //Retrieve Location Settings Data Provider
    @DataProvider(name = "locationSettingsData")
    public Object[][] locationSettingsData() {
        return new Object[][]{
                {"List_TC_001 - Valid location", "L7NA82DE840VB", 200, true},
                {"List_TC_002 - Invalid location (not found)", "L7NA82DE840Vb", 404, false}
        };
    }
}
