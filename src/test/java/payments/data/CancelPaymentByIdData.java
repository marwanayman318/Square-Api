package payments.data;

import POJOS.CancelPayment.CancelPaymentRequest;
import org.testng.annotations.DataProvider;

import java.util.UUID;

public class CancelPaymentByIdData {
    @DataProvider(name = "cancelPaymentByIdData")
    public Object[][] cancelPaymentByIdData() {
        return new Object[][]{
                {
                        "CancelID_TC_020 - Cancel Approved Payment",
                        "VW07oXYkp7NcZ76xyCB2MphAhnRZY",
                        new CancelPaymentRequest(UUID.randomUUID().toString()),
                        200,
                        "CANCELED"
                },
                {
                        "CancelID_TC_021 - Cancel Completed Payment",
                        "f7MhX4mPdLwbF2vibm6Fm0RsbFAZY",
                        new CancelPaymentRequest(UUID.randomUUID().toString()),
                        400,
                        "COMPLETED"
                }
        };
    }
}
