package customer.Data;

import org.testng.annotations.DataProvider;


public class ListCustomersData {

    @DataProvider(name = "listCustomersData")
    public Object[][] listCustomersData() {
        return new Object[][]{
                {"LC_TC_001", 200, true}
        };
    }
}

