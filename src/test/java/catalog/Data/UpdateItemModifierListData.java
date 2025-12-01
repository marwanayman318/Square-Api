package catalog.Data;

import org.testng.annotations.DataProvider;

public class UpdateItemModifierListData {
    @DataProvider(name = "updateItemModifierListData")
    public static Object[][] updateItemModifierListData() {
        String objectId1 = "D6UAYMZM4PGPHZH7UEPEZOTC";
        String objectId2 = "7T3XMO4GE36JSP5K2LKUDJAX";
        String body1 = "{ \"item_ids\": [\n" +
                "\"" + objectId1 + "\",\n" +
                "\"" + objectId2 + "\"\n" +
                "],\n" +
                "\"modifier_lists_to_enable\": [\n" +
                "\"UZXCTG3AKSA3PSJR4FRSRBQX\"\n" +
                "], \"modifier_lists_to_disable\": \n[" +
                "] }";
        String body2 = "{ \"item_ids\": [] }";
        return new Object[][]{
                {"UIML_TC_025", body1, 200, true},
                {"UIML_TC_026", body2, 400, false}
        };
    }
}
