package catalog.Data;

import org.testng.annotations.DataProvider;

public class SearchCatalogItemData {
    @DataProvider(name = "searchCatalogItemData")
    public static Object[][] searchCatalogItemData() {
        String body1 = "{ \"text_filter\": \"tea\" }";
        String body2 = "{ \"text_filter\": \"teaa\" }";
        return new Object[][]{
                {"SCI_TC_019", body1, 200, true},
                {"SCI_TC_020", body2, 200, true}
        };
    }
}
