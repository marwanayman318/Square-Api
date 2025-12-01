package customer.Data;

import org.testng.annotations.DataProvider;

public class SearchCustomerData {
    @DataProvider(name = "searchCustomerData")
    public Object[][] searchCustomerData() {
        String body = "{ \"query\": { \"filter\": { \"email_address\": { \"fuzzy\": \"example.com\" }, \"creation_source\": { \"values\": [ \"THIRD_PARTY\" ], \"rule\": \"INCLUDE\" } } } }";
        String body2 = "{ \"query\": { \"filter\": { \"creation_source\": { \"values\": [ \"THIRD_PARTY\" ], \"rule\": \"INCLUDE\" } } }, \"limit\": 0 }";
        return new Object[][]{
                {"SC_TC_014", body, 200, true},
                {"SC_TC_015", body2, 400, false}
        };
    }
}
