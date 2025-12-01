package customer.Data;

import Helper.CreateCustomerHelper;
import org.testng.annotations.DataProvider;

public class BulkRetrieveData {
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
}
