package customer.Data;

import Helper.CreateCustomerHelper;
import org.testng.annotations.DataProvider;

public class DeleteCustomerData {
    @DataProvider(name = "deleteCustomerData")
    public Object[][] deleteCustomerData() {
        String id = CreateCustomerHelper.createCustomerId();
        String id2 = "8MFW3ZKSDEMJXYPMYX6E5RWAA9";
        return new Object[][]{
                {"DC_TC_016", id, 200, true},
                {"DC_TC_017", id2, 404, false}
        };
    }}
