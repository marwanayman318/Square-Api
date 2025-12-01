package refunds.data;

import org.testng.annotations.DataProvider;

public class ListRefundsData {
    @DataProvider(name = "listRefundsData")
    public Object[][] listRefundsData() {
        return new Object[][]{
                {
                        "List_TC_001 - Get all refunds",
                        null,
                        200,
                        true // Expect refunds array
                },
                {
                        "List_TC_002 - Verify refund transitions",
                        null,
                        200,
                        true
                },
                {
                        "List_TC_003 - Filter refunds by date range",
                        "?begin_time=2025-11-06T00:00:00Z&end_time=2025-11-07T23:59:59Z",
                        200,
                        true
                },
                {
                        "List_TC_004 - Invalid query parameter",
                        "?status=INVALID_STATUS",
                        400,
                        false
                }
        };
    }
}
