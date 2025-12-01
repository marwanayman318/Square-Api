package customer.Data;

import Helper.CreateCustomerHelper;
import Helper.CreateGroupHelper;
import org.testng.annotations.DataProvider;

import java.util.List;

public class ListCustomersData {

    @DataProvider(name = "listCustomersData")
    public Object[][] listCustomersData() {
        return new Object[][]{
                {"LC_TC_001", 200, true}
        };
    }

    @DataProvider(name = "listCustomersLimitZero")
    public Object[][] listCustomersLimitZero() {
        return new Object[][]{
                {"LC_TC_002", 0, 400, false}
        };
    }

    @DataProvider(name = "createCustomerData")
    public Object[][] createCustomerData() {
        String validBody = "{\n" +
                "  \"given_name\": \"Amelia\",\n" +
                "  \"family_name\": \"Earhart\",\n" +
                "  \"email_address\": \"Amelia.Earhart@example.com\",\n" +
                "  \"address\": {\n" +
                "    \"address_line_1\": \"500 Electric Ave\",\n" +
                "    \"address_line_2\": \"Suite 600\",\n" +
                "    \"locality\": \"New York\",\n" +
                "    \"administrative_district_level_1\": \"NY\",\n" +
                "    \"postal_code\": \"10003\",\n" +
                "    \"country\": \"US\"\n" +
                "  }\n" +
                "}";
        String invalidEmail = "{ \"given_name\": \"Amelia\", \"family_name\": \"Earhart\", \"email_address\": \"Amelia.Earhartexample.com\" }";
        String missingIdentifiers = "{ \"note\": \"No identifiers\" }";

        return new Object[][]{
                {"CC_TC_003", validBody, 200, true},
                {"CC_TC_004", invalidEmail, 400, false},
                {"CC_TC_005", missingIdentifiers, 400, false}
        };
    }

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

    @DataProvider(name = "bulkDeleteData")
    public Object[][] bulkDeleteData() {
        String id1 = CreateCustomerHelper.createCustomerId();
        String id2 = CreateCustomerHelper.createCustomerId();

        String body = "{ \"customer_ids\": [ \"" + id1 + "\", \"" + id2 + "\" ] }";
        String body2 = "{ \"customer_ids\": [ \"KKAA3J9P8WASDFF51AXGGT868W\", \"HJV3ZJVYFBC4MNE6MED0HE2S6M\" ] }";

        return new Object[][]{
                {"BDC_TC_008", body, 200, true},
                {"BDC_TC_009", body2, 200, true}
        };
    }

    @DataProvider(name = "bulkRetrieveData")
    public Object[][] bulkRetrieveData() {
        String id1 = CreateCustomerHelper.createCustomerId();
        String id2 = CreateCustomerHelper.createCustomerId();

        String body = "{ \"customer_ids\": [ \"" + id1 + "\", \""+ id2 +"\" ] }";
        String body2 = "{ \"customer_ids\": [ \"CTDHS36MZAT6EYMWZPNB5AAVJc\", \"" + id1 + "\" ] }";
        return new Object[][]{
                {"BRC_TC_010", body, 200, true},
                {"BRC_TC_011", body2, 200, true}
        };
    }

    @DataProvider(name = "bulkUpdateData")
    public Object[][] bulkUpdateData() {
        List<String> id = CreateCustomerHelper.createCustomerIds(4);

        String customer1 = id.get(0);
        String customer2 = id.get(1);
        String customer3 = id.get(2);
        String customer4 = id.get(3);

        String body1 = "{\n" +
                "  \"customers\": {\n" +
                "    \"" + customer1 + "\": {\n" +
                "      \"phone_number\": null,\n" +
                "      \"email_address\": \"New.Amelia.Earhart@example.com\",\n" +
                "      \"note\": \"updated customer note\",\n" +
                "      \"version\": null\n" +
                "    }\n" +
                "   }, \n" +
                "  \"customers\": {\n" +
                "    \"" + customer2 + "\": {\n" +
                "      \"phone_number\": null,\n" +
                "      \"email_address\": \"Amelia.Earhart@example.com\",\n" +
                "      \"note\": \"updated customer note\",\n" +
                "      \"version\": null\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String body2 = "{\n" +
                "  \"customers\": {\n" +
                "    \"" + customer3 + "\": {\n" +
                "      \"phone_number\": null,\n" +
                "      \"email_address\": \"invalid-email\",\n" +
                "      \"note\": \"invalid test\",\n" +
                "      \"version\": null\n" +
                "    }\n" +
                "   },\n"+
                "  \"customers\": {\n" +
                "    \"" + customer4 + "\": {\n" +
                "      \"phone_number\": null,\n" +
                "      \"email_address\": \"invalid@email\",\n" +
                "      \"note\": \"invalid test\",\n" +
                "      \"version\": null\n" +
                "    }\n" +
                "  }\n" +
                "}";

        return new Object[][]{
                {"BUC_TC_012", body1, 200, true},
                {"BUC_TC_013", body2, 200, true}
        };
    }


    @DataProvider(name = "searchCustomerData")
    public Object[][] searchCustomerData() {
        String body = "{ \"query\": { \"filter\": { \"email_address\": { \"fuzzy\": \"example.com\" }, \"creation_source\": { \"values\": [ \"THIRD_PARTY\" ], \"rule\": \"INCLUDE\" } } } }";
        String body2 = "{ \"query\": { \"filter\": { \"creation_source\": { \"values\": [ \"THIRD_PARTY\" ], \"rule\": \"INCLUDE\" } } }, \"limit\": 0 }";
        return new Object[][]{
                {"SC_TC_014", body, 200, true},
                {"SC_TC_015", body2, 400, false}
        };
    }

    @DataProvider(name = "singleCustomerData")
    public Object[][] singleCustomerData() {
        String id = CreateCustomerHelper.createCustomerId();
        String id2 = "YJCFZ5FZVEP2HJEXPJ9FF9FDVg";

        return new Object[][]{
                {"RCBI_TC_018", id, 200, true},
                {"RCBI_TC_019",id2 , 404, false}
        };
    }

    @DataProvider(name = "deleteCustomerData")
    public Object[][] deleteCustomerData() {
        String id = CreateCustomerHelper.createCustomerId();
        String id2 = "8MFW3ZKSDEMJXYPMYX6E5RWAA9";
        return new Object[][]{
                {"DC_TC_016", id, 200, true},
                {"DC_TC_017", id2, 404, false}
        };
    }

    @DataProvider(name = "updateCustomerData")
    public Object[][] updateCustomerData() {
        List<String> id = CreateCustomerHelper.createCustomerIds(2);

        String body = "{ \"given_name\": \"Omar\", \"email_address\": \"Omar@example.com\" }";
        return new Object[][]{
                {"UCBI_TC_020", id.get(0), body, 200, true},
                {"UCBI_TC_021", id.get(1), "{}", 400, false}
        };
    }

    @DataProvider(name = "groupData")
    public Object[][] groupData() {
        List<String> custid = CreateCustomerHelper.createCustomerIds(4);
        List<String> groupid = CreateGroupHelper.createGroupIds(2);
        return new Object[][]{
                {"add TC_022", custid.get(0), groupid.get(0), 200, true},
                {"add TC_023", custid.get(1), "7RATVWBWHKBJABADBXABYBBPRk", 404, false},
                {"RGFC_TC_024", custid.get(2), groupid.get(groupid.size() - 1), 200, true},
                {"RGFC_TC_025", custid.get(custid.size() - 1), "7RATVWBWHKBJABADBXABYBBPRk", 404, false}
        };
    }
    }

