package customer.Data;

import org.testng.annotations.DataProvider;

public class BulkCreateData {
    @DataProvider(name = "bulkCreateData")
    public Object[][] bulkCreateData() {
        String bulkBody = "{\n" +
                "  \"customers\": {\n" +
                "    \"a1b2c3d4-e5f6-7g8h\": {\n" +
                "      \"given_name\": \"Amelia\",\n" +
                "      \"family_name\": \"Earhart\",\n" +
                "      \"email_address\": \"Amelia.Earhart@example.com\"\n" +
                "    },\n" +
                "    \"b1c2d3e4-f5g6-7h8i\": {\n" +
                "      \"given_name\": \"Marie\",\n" +
                "      \"family_name\": \"Curie\",\n" +
                "      \"email_address\": \"Marie.Curie@example.com\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        String invalidbody = "{\n" +
                "  \"customers\": {\n" +
                "    \"8bb76c4f-e35d-4c5b-90de-1194cd9179f0\": {\n" +
                "      \"given_name\": \"Amelia\",\n" +
                "      \"family_name\": \"Earhart\",\n" +
                "      \"email_address\": \"Amelia.Earhartexample.com\"\n" +
                "    },\n" +
                "    \"d1689f23-b25d-4932-b2f0-aed00f5e2028\": {\n" +
                "      \"given_name\": \"Marie\",\n" +
                "      \"family_name\": \"Curie\",\n" +
                "      \"email_address\": \"Marie.Curie@example.com\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        return new Object[][]{
                {"BCC_TC_006", bulkBody, 200, true},
                {"BCC_TC_007", invalidbody, 200, true}
        };
    }
}
