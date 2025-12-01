package payments.data;

import POJOS.CancelPayment.CancelPaymentRequest;
import org.testng.annotations.DataProvider;

public class CancelPaymentData {
    @DataProvider(name = "CancelPaymentData")
    public Object[][] cancelPaymentData() {
        return new Object[][]{
                {"Cancel_TC_015", new CancelPaymentRequest("vnmrGSCPp0RC6GQFar0LocVWUpJZY"), 200, null}
        };
    }
}
