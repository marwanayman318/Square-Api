package customer.Data;

import org.testng.annotations.DataProvider;

public class listCustomersLimitData {
    @DataProvider(name = "listCustomersLimitZero")
    public Object[][] listCustomersLimitZero() {
        return new Object[][]{
                {"LC_TC_002", 0, 400, false}
        };
    }
}
