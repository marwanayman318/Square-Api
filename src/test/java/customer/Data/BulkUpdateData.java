package customer.Data;

import Helper.CreateCustomerHelper;
import org.testng.annotations.DataProvider;

import java.util.List;

public class BulkUpdateData {
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
}
