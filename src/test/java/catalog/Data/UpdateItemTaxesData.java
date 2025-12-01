package catalog.Data;

import org.testng.annotations.DataProvider;

public class UpdateItemTaxesData {
    @DataProvider(name = "updateItemTaxesData")
    public static Object[][] updateItemTaxesData() {
        String body1 = "{ \"item_ids\": [\n" +
                "\"FGIHTKBZMHKD23CQVSMWL4QS\",\n" +
                "\"3522NLC6KNC5WGWRLTRX6ET3\"\n" +
                "],\n" +
                "\"taxes_to_enable\": [\n" +
                "\"QDMYBIRY4HC6XHOVGOEBJF2L\"\n" +
                "   ]\n"+
                "}";
        String body2 = "{ \"item_ids\": [] }";
        return new Object[][]{
                {"UIT_TC_027", body1, 200, true},
                {"UIT_TC_028", body2, 400, false}
        };
    }
}
