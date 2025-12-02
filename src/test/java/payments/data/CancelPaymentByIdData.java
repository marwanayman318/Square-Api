package payments.data;

import POJOS.CancelPayment.CancelPaymentRequest;
import org.testng.annotations.DataProvider;
import java.util.UUID;

public class CancelPaymentByIdData {

    @DataProvider(name = "CancelPaymentByIdData")
    public Object[][] cancelPaymentByIdData() {
        return new Object[][]{
                {
                        "TC_Payment_020",
                        "VW07oXYkp7NcZ76xyCB2MphAhnRZY",
                        new CancelPaymentRequest(UUID.randomUUID().toString()),
                        200,
                        "CANCELED"
                },
                {
                        "TC_Payment_021 - Cancel Completed Payment",
                        "f7MhX4mPdLwbF2vibm6Fm0RsbFAZY",
                        new CancelPaymentRequest(UUID.randomUUID().toString()),
                        400,
                        "COMPLETED"
                }
        };
    }
}
