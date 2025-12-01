package customer.Data;

import Helper.CreateCustomerHelper;
import Helper.CreateGroupHelper;
import org.testng.annotations.DataProvider;

import java.util.List;

public class GroupData {
    @DataProvider(name = "groupData")
    public Object[][] groupData() {
        List<String> custid = CreateCustomerHelper.createCustomerIds(4);
        List<String> groupid = CreateGroupHelper.createGroupIds(2);
        return new Object[][]{
                {"add TC_022", custid.get(0), groupid.get(0), 200, true},
                {"add TC_023", custid.get(1), "7RATVWBWHKBJABADBXABYBBPRk", 404, false},
                {"RGFC_TC_024", custid.get(2), groupid.get(groupid.size() - 1), 200, true},
                {"RGFC_TC_025", custid.get(custid.size() - 1), "7RATVWBWHKBJABADBXABYBBPRk", 404, false}
        };
    }
}
