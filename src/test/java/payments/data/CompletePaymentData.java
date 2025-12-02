package payments.data;

import POJOS.CancelPayment.CancelPaymentRequest;
import org.testng.annotations.DataProvider;

import java.util.UUID;

public class CompletePaymentData {
    @DataProvider(name = "completePaymentData")
    public Object[][] completePaymentData() {
        return new Object[][]{
                {
                        "TC_Payment_022",
                        "f7MhX4mPdLwbF2vibm6Fm0RsbFAZY",
                        new CancelPaymentRequest(UUID.randomUUID().toString()),
                        200,
                        "COMPLETED"
                },
                {
                        "TC_Payment_023",
                        "VW07oXYkp7NcZ76xyCB2MphAhnRZY",
                        new CancelPaymentRequest(UUID.randomUUID().toString()),
                        400,
                        "CANCELED"
                }
        };
    }
}
