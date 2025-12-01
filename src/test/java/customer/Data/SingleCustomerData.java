package customer.Data;

import Helper.CreateCustomerHelper;
import org.testng.annotations.DataProvider;

public class SingleCustomerData {
    @DataProvider(name = "singleCustomerData")
    public Object[][] singleCustomerData() {
        String id = CreateCustomerHelper.createCustomerId();
        String id2 = "YJCFZ5FZVEP2HJEXPJ9FF9FDVg";

        return new Object[][]{
                {"RCBI_TC_018", id, 200, true},
                {"RCBI_TC_019",id2 , 404, false}
        };
    }
}
