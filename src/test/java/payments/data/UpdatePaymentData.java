package payments.data;

import POJOS.UpdatePayment.UpdatePayment;
import org.testng.annotations.DataProvider;

import java.util.UUID;

import static Helper.CreatePaymentHelper.createPayment;

public class UpdatePaymentData {

    @DataProvider(name = "updatePaymentsData")
    public Object[][] updatePaymentsData() {
        String paymentId1 = createPayment(1000,"USD","cnon:card-nonce-ok");
        return new Object[][]{
                {
                        "Update_TC_018 - Update approved payment",
                        paymentId1,
                        new UpdatePayment(UUID.randomUUID().toString(), 1000, "USD"),
                        200,
                        true
                },
                {
                        "Update_TC_019 - Update completed payment",
                        "dEVVEccVU80TnKjmaDcFuepPttTZY",
                        new UpdatePayment(UUID.randomUUID().toString(), 1000, "USD"),
                        400,
                        false
                }
        };
    }
}
