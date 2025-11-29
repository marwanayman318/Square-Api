package catalog.Data;

import Helper.CreateItemHelper;
import org.testng.annotations.DataProvider;
import utilities.Config;
import java.util.UUID;


public class CatalogData {

    @DataProvider(name = "createCatalogObjectData")
    public static Object[][] catalogData() {
        String body1 = "{\n" +
                "  \"idempotency_key\": \"{{$guid}}\",\n" +
                "  \"object\": {\n" +
                "    \"id\": \"#Cocoa\",\n" +
                "    \"type\": \"ITEM\",\n" +
                "    \"item_data\": {\n" +
                "      \"name\": \"Cocoa\",\n" +
                "      \"variations\": [\n" +
                "        {\n" +
                "          \"id\": \"#Small\",\n" +
                "          \"type\": \"ITEM_VARIATION\",\n" +
                "          \"item_variation_data\": {\n" +
                "            \"item_id\": \"#Cocoa\",\n" +
                "            \"name\": \"Small\",\n" +
                "            \"pricing_type\": \"VARIABLE_PRICING\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String body2 = "{\n" +
                "  \"idempotency_key\": \"{{$guid}}\",\n" +
                "  \"object\": {\n" +
                "    \"id\": \"#Cocoa\",\n" +
                "    \"item_data\": {\n" +
                "      \"variations\": [\n" +
                "        {\n" +
                "          \"id\": \"#Small\",\n" +
                "          \"type\": \"ITEM_VARIATION\",\n" +
                "          \"item_variation_data\": {\n" +
                "            \"item_id\": \"#Cocoa\",\n" +
                "            \"name\": \"Small\",\n" +
                "            \"pricing_type\": \"VARIABLE_PRICING\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        return new Object[][]{
                {"UCO_TC_001", body1, 200, true},
                {"UCO_TC_002", body2, 400, false}
        };
    }

    @DataProvider(name = "batchUpsertCatalogObjectData")
    public static Object[][] batchUpsertCatalogObject() {
        String body1 = "{\n" +
                "  \"idempotency_key\": \"{{$guid}}\",\n" +
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

    @DataProvider(name = "retrieveCatalogObjectData")
    public static Object[][] retrieveCatalogObject() {
        String orderId = CreateItemHelper.getAnyCatalogObjectId();

        return new Object[][]{
                {"RCO_TC_005", orderId, 200, true},
                {"RCO_TC_006", "ZAXFYNBOY7BNEM66FDW2QDEm", 404, false}
        };

    }

    @DataProvider(name = "deleteCatalogObjectData")
    public static Object[][] deleteCatalogObject() {
        String orderId = CreateItemHelper.getAnyCatalogObjectId();

        return new Object[][]{
                {"DCO_TC_007", orderId, 200, true},
                {"DCO_TC_008", "ZAXFYNBOY7BNEM66FDW2QDEm", 404, false}
        };

    }

    @DataProvider(name = "listCatalogObjectData")
    public static Object[][] listCatalogObject() {
        String token1 = Config.TOKEN;
        String token2 = "invalid-token";
        return new Object[][]{
                {"LC_TC_009", token1, 200, true},
                {"LC_TC_010", token2, 401, false}
        };
    }

    @DataProvider(name = "batchRetrieveCatalogObjectData")
    public static Object[][] batchRetrieveCatalogObjectData() {
        String orderId1 = CreateItemHelper.getAnyCatalogObjectId();
        String orderId2 = CreateItemHelper.getAnyCatalogObjectId();
        String body1 = "{\n" +
                "  \"object_ids\": [\n" +
                "   \"" + orderId1 + "\",\n" +
                "    \"" + orderId2 + "\" \n" +
                "  ]\n" +
                "}";
        String body2 = "{ \"object_ids\": [] }";
        return new Object[][]{
                {"LC_TC_011", body1, 200, true},
                {"LC_TC_012", body2, 400, false}
        };

    }

    @DataProvider(name = "updateCatalogImageData")
    public static Object[][] updateCatalogImageData() {
        String imagePath1 = "src/test/resources/TC001_succ.png";
        String imagePath2 = "src/test/resources/TC007_succ.png";
        String objectId = CreateItemHelper.getAnyCatalogObjectId();
        String request1 =
                "{\n" +
                        "  \"idempotency_key\": \"" + UUID.randomUUID() + "\",\n" +
                        "  \"object_id\": \"" + objectId + "\",\n" +
                        "  \"image\": {\n" +
                        "  \"type\": \"IMAGE\",\n" +
                        "  \"id\":\"#Coffee_Image\"," +
                        "    \"image_data\": {\n" +
                        "      \"name\": \"Coffee\",\n" +
                        "      \"caption\": \"A picture of a cup of coffee\"\n" +
                        "    }" +
                        "  }" +
                        "}";

        String request2 =
                "{\n" +
                        "  \"idempotency_key\": \"" + UUID.randomUUID() + "\",\n" +
                        "  \"object_id\": \"" + objectId + "\",\n" +
                        "  \"image\": {\n" +
                        "    \"type\": \"IMAGE\",\n" +
                        "    \"image_data\": {\n" +
                        "      \"name\": \"Coffee\",\n" +
                        "      \"caption\": \"A picture of a cup of coffee\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}";

        return new Object[][]{
                {"UCI_TC_013", "XZPLLCVPQLNSMJDHLVCCLY7Y", imagePath1, request1, 200, true},
                {"UCI_TC_014", "invalid_image_id", imagePath2, request2, 400, false}
        };

    }

    @DataProvider(name = "searchCatalogObjectsData")
    public static Object[][] searchCatalogObjectsData() {
        String body1 = "{\n " +
                "   \"object_types\": [\n " +
                "       \"ITEM\" \n " +
                "      ],\n" +
                " \"query\": {\n" +
                " \"prefix_query\": {\n" +
                " \"attribute_name\": \"name\", \n " +
                "\"attribute_prefix\": \"tea\" \n" +
                "       }\n" +
                "   },\n" +
                " \"limit\": 5 \n" +
                "}";
        String body2 = "{\n" +
                "   \"object_types\": [\n" +
                "   \"ITEM\" \n" +
                "   ],\n" +
                "   \"query\": {\n" +
                "   \"exact_query\": {\n" +
                "   \"attribute_name\": \"wrong_field\", \n" +
                "   \"attribute_value\": \"Value\" \n" +
                "}  \n" +
                " } \n" +
                " }";
        return new Object[][]{
                {"SCO_TC_015", body1, 200, true},
                {"SCO_TC_016", body2, 400, false}
        };

    }

    @DataProvider(name = "catalogInfoData")
    public static Object[][] catalogInfoData() {
        String token1 = Config.TOKEN;
        String token2 = "invalid-token";
        return new Object[][]{
                {"CI_TC_017", token1, 200, true},
                {"CI_TC_018", token2, 401, false}
        };
    }

    @DataProvider(name = "searchCatalogItemData")
    public static Object[][] searchCatalogItemData() {
        String body1 = "{ \"text_filter\": \"tea\" }";
        String body2 = "{ \"text_filter\": \"teaa\" }";
        return new Object[][]{
                {"SCI_TC_019", body1, 200, true},
                {"SCI_TC_020", body2, 200, true}
        };
    }

    @DataProvider(name = "batchDeleteCatalogObjectsData")
    public static Object[][] batchDeleteCatalogObjectsData() {
        String objectId1 = CreateItemHelper.getAnyCatalogObjectId();
        String objectId2 = CreateItemHelper.getAnyCatalogObjectId();
        String body1 = "{\n " +
                " \"object_ids\": [ \"" + objectId1 + "\" ,\n" +
                " \"" + objectId2 + "\" ] \n" +
                "}";
        String body2 = "{ \"object_ids\": [] }";
        return new Object[][]{
                {"BDCO_TC_021", body1, 200, true},
                {"BDCO_TC_022", body2, 400, false}
        };
    }

    @DataProvider(name = "createCatalogImageData")
    public static Object[][] createCatalogImageData() {
        String idempotencyKey = UUID.randomUUID().toString();
        String imagePath1 = "src/test/resources/TC001_succ.png";
        String imagePath2 = "src/test/resources/Marwan-Ayman-CV.pdf";
        String objectId1 = CreateItemHelper.getAnyCatalogObjectId();
        String body1 = "{ \"idempotency_key\":\"" + idempotencyKey + "\", \n" +
                "\"image\":{\n" +
                " \"type\":\"IMAGE\",\n" +
                "\"id\":\"#Coffee_Image\",\n" +
                "\"image_data\":{\n" +
                " \"name\":\"Coffee Image\" \n" +
                "       }\n" +
                "   },\n" +
                " \"object_id\":\"" + objectId1 + "\" \n" +
                "}";
        return new Object[][]{
                {"CCI_TC_023", imagePath1,body1, 200, true},
                {"CCI_TC_024", imagePath2,body1, 400, false}
        };
    }

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