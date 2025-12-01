package catalog.Data;

import org.testng.annotations.DataProvider;

import java.util.UUID;

public class BatchUpsertCatalogObjectData {
    @DataProvider(name = "batchUpsertCatalogObjectData")
    public static Object[][] batchUpsertCatalogObject() {
        String idempotencyKey = UUID.randomUUID().toString();
        String body1 = "{\n" +
                "  \"idempotency_key\":\""+idempotencyKey +"\",\n"+
                "  \"batches\": [\n" +
                "    {\n" +
                "      \"objects\": [\n" +
                "        {\n" +
                "          \"type\": \"ITEM\",\n" +
                "          \"id\": \"#Tea\",\n" +
                "          \"item_data\": {\n" +
                "            \"name\": \"Tea\",\n" +
                "            \"tax_ids\": [\"#SalesTax\"],\n" +
                "            \"variations\": [\n" +
                "              {\n" +
                "                \"type\": \"ITEM_VARIATION\",\n" +
                "                \"id\": \"#Tea_Mug\",\n" +
                "                \"item_variation_data\": { \"price_money\": { \"amount\": 150, \"currency\": \"USD\" } }\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"ITEM\",\n" +
                "          \"id\": \"#Coffee\",\n" +
                "          \"item_data\": {\n" +
                "            \"name\": \"Coffee\",\n" +
                "            \"tax_ids\": [\"#SalesTax\"],\n" +
                "            \"variations\": [\n" +
                "              { \"type\": \"ITEM_VARIATION\", \"id\": \"#Coffee_Regular\", \"item_variation_data\": { \"price_money\": { \"amount\":250, \"currency\":\"USD\" } } },\n" +
                "              { \"type\": \"ITEM_VARIATION\", \"id\": \"#Coffee_Large\", \"item_variation_data\": { \"price_money\": { \"amount\":350, \"currency\":\"USD\" } } }\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        { \"type\": \"CATEGORY\", \"id\": \"#Beverages\", \"category_data\": { \"name\": \"Beverages\" } },\n" +
                "        { \"type\": \"TAX\", \"id\": \"#SalesTax\", \"tax_data\": { \"name\":\"SalesTax\",\"calculation_phase\":\"TAX_SUBTOTAL_PHASE\",\"inclusion_type\":\"ADDITIVE\",\"percentage\":\"5.0\" } }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String body2 = "{\n" +
                "  \"idempotency_key\": \"lkz7TACfImbIb3iGfDfEPewWtFOZY\",\n" +
                "  \"batches\": [ { \"objects\": [ { \"type\": \"ITEM\", \"id\": \"#Tea\", \"item_data\": { \"name\": \"Tea\", \"variations\": [ { \"type\": \"ITEM_VARIATION\", \"id\": \"#Tea_Mug\", \"item_variation_data\": { \"pricing_type\": \"FIXED_PRICING\", \"price_money\": { \"amount\": 150, \"currency\": \"USD\" } } } ] } }, { \"type\":\"CATEGORY\",\"id\":\"#Beverages\",\"category_data\":{\"name\":\"Beverages\"}}, {\"type\":\"TAX\",\"id\":\"#SalesTax\",\"present_at_all_locations\":true,\"tax_data\":{\"name\":\"Sales Tax\",\"calculation_phase\":\"TAX_SUBTOTAL_PHASE\",\"inclusion_type\":\"ADDITIVE\",\"percentage\":\"5.0\"}} ] } ]\n" +
                "}";
        return new Object[][]{
                {"BUCO_TC_003", body1, 200, true},
                {"BUCO_TC_004", body2, 400, false}
        };

    }
}
