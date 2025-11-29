package checkout.Data;

import org.testng.annotations.DataProvider;

public class RetrievePaymentLinkData {

    @DataProvider(name = "retrievePaymentLinkData")
    public Object[][] retrievePaymentLinkData() {
        return new Object[][]{

                {
                        "RPL_TC_016 - Retrieve valid payment link",
                        "LYZAHQU77DP6F2OV",
                        200,
                        true
                },

                {
                        "RPL_TC_017 - Retrieve non-existing payment link",
                        "EZROVE6YV3ZQ7QBV5",
                        404,
                        false
                }

        };
    }
}
