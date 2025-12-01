package payments.data;

import org.testng.annotations.DataProvider;

public class GetPaymentsData {
    @DataProvider(name = "getPaymentsData")
    public Object[][] getPaymentsData() {
        return new Object[][]{
                {"Get_TC_016 - Valid Payment ID", "7RW8DEuEAVCBSiupp5go5MGjdWHZY", 200, true},
                {"Get_TC_017 - Invalid Payment ID", "7RW8DEuEAVCBSiupp5go5MGjdWHZy", 404, false}
        };
    }
}
