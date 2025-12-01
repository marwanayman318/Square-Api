package catalog.Data;

import org.testng.annotations.DataProvider;
import java.util.UUID;


public class CreateCatalogObjectData {

    @DataProvider(name = "createCatalogObjectData")
    public static Object[][] catalogData() {
        String idempotencyKey = UUID.randomUUID().toString();
        String body1 = "{\n" +
                "  \"idempotency_key\": \""+idempotencyKey +"\",\n" +
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
                "  \"idempotency_key\":\""+idempotencyKey +"\",\n"+
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
}