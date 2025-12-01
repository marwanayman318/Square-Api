package customer.Data;

import Helper.CreateCustomerHelper;
import org.testng.annotations.DataProvider;

import java.util.List;

public class UpdateCustomerData {
    @DataProvider(name = "updateCustomerData")
    public Object[][] updateCustomerData() {
        List<String> id = CreateCustomerHelper.createCustomerIds(2);

        String body = "{ \"given_name\": \"Omar\", \"email_address\": \"Omar@example.com\" }";
        return new Object[][]{
                {"UCBI_TC_020", id.get(0), body, 200, true},
                {"UCBI_TC_021", id.get(1), "{}", 400, false}
        };
    }
}
