package customer.Data;

import Helper.CreateCustomerHelper;
import org.testng.annotations.DataProvider;

public class BulkDeleteData {
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
}
