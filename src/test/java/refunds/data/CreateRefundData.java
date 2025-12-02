package refunds.data;

import Helper.CreatePaymentHelper;
import POJOS.createPayment.PaymentRequest;
import POJOS.createPayment.money;
import org.testng.annotations.DataProvider;

import java.util.UUID;

public class CreateRefundData {
    @DataProvider(name = "CreateRefundData")
    public Object[][] createRefundData() {

        String idempotencyKey = UUID.randomUUID().toString();

        //valid refund request
        PaymentRequest validRefund = new PaymentRequest();
        validRefund.setIdempotency_key(idempotencyKey);
        validRefund.setPayment_id(CreatePaymentHelper.createPayment(10000, "USD", "cnon:card-nonce-ok", true));
        money money1 = new money();
        money1.setAmount(1000);
        money1.setCurrency("USD");
        validRefund.setAmount_money(money1);

        //cannot refund more than the payment amount
        PaymentRequest overRefund = new PaymentRequest();
        overRefund.setIdempotency_key(UUID.randomUUID().toString());
        overRefund.setPayment_id(CreatePaymentHelper.createPayment(200, "USD", "cnon:card-nonce-ok", true));
        money money2 = new money();
        money2.setAmount(500);
        money2.setCurrency("USD");
        overRefund.setAmount_money(money2);

        //missing payment id
        PaymentRequest missingPaymentId = new PaymentRequest();
        missingPaymentId.setIdempotency_key(UUID.randomUUID().toString());
        money money3 = new money();
        money3.setAmount(500);
        money3.setCurrency("USD");
        missingPaymentId.setAmount_money(money3);

        //invalid amount (non-integer)
        PaymentRequest invalidAmount = new PaymentRequest();
        invalidAmount.setIdempotency_key(UUID.randomUUID().toString());
        invalidAmount.setPayment_id(CreatePaymentHelper.createPayment(100, "USD", "cnon:card-nonce-ok", true));
        money money4 = new money();
        money4.setAmount(-1500);
        money4.setCurrency("USD");
        invalidAmount.setAmount_money(money4);

        //zero amount
        PaymentRequest zeroAmount = new PaymentRequest();
        zeroAmount.setIdempotency_key(UUID.randomUUID().toString());
        zeroAmount.setPayment_id(CreatePaymentHelper.createPayment(10000, "USD", "cnon:card-nonce-ok", true));
        money money5 = new money();
        money5.setAmount(0);
        money5.setCurrency("USD");
        zeroAmount.setAmount_money(money5);

        return new Object[][]{
                {"TC_Payment_005", validRefund, 200, "COMPLETED"},
                {"TC_Payment_006", overRefund, 400, null},
                {"TC_Payment_007", missingPaymentId, 400, null},
                {"TC_Payment_008", invalidAmount, 400, null},
                {"TC_Payment_009", zeroAmount, 400, null}
        };
    }



}