package refunds.data;

import org.testng.annotations.DataProvider;

public class ListRefundsData {
    @DataProvider(name = "listRefundsData")
    public Object[][] listRefundsData() {
        return new Object[][]{
                {"TC_Refund_001", null, 200, true},
                {"TC_Refund_002", null, 200, true},
                {"TC_Refund_003", "?begin_time=2025-11-06T00:00:00Z&end_time=2025-11-07T23:59:59Z", 200, true},
                {"TC_Refund_004", "?status=INVALID_STATUS", 400, false}
        };
    }
}
