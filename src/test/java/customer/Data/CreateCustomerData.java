package customer.Data;

import org.testng.annotations.DataProvider;

public class CreateCustomerData {
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
}
