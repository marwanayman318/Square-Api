package refunds.data;

import POJOS.createPayment.PaymentRequest;
import POJOS.createPayment.money;
import org.testng.annotations.DataProvider;

import java.util.UUID;

public class CreateRefundData {
    @DataProvider(name = "createRefundData")
    public Object[][] createRefundData() {
        // Generate dynamic idempotency keys
        String idempotencyKey = UUID.randomUUID().toString();

        PaymentRequest validRefund = new PaymentRequest();
        validRefund.setIdempotency_key(idempotencyKey);
        validRefund.setPayment_id("9Y9g8c5vYs8DYnYWZy85f9sH23YZY");
        money money1 = new money();
        money1.setAmount(1000);
        money1.setCurrency("USD");
        validRefund.setAmount_money(money1);

        PaymentRequest overRefund = new PaymentRequest();
        overRefund.setIdempotency_key(UUID.randomUUID().toString());
        overRefund.setPayment_id("jxqDjGmFEM28DZalR99PsckoWqGZY");
        money money2 = new money();
        money2.setAmount(500);
        money2.setCurrency("USD");
        overRefund.setAmount_money(money2);

        PaymentRequest missingPaymentId = new PaymentRequest();
        missingPaymentId.setIdempotency_key(UUID.randomUUID().toString());
        money money3 = new money();
        money3.setAmount(500);
        money3.setCurrency("USD");
        missingPaymentId.setAmount_money(money3);


        PaymentRequest invalidAmount = new PaymentRequest();
        invalidAmount.setIdempotency_key(UUID.randomUUID().toString());
        invalidAmount.setPayment_id("tMBhhDtYbWa78YNJoUEnVnqiWqBZY");
        money money4 = new money();
        money4.setAmount((int) 150.5);
        money4.setCurrency("USD");
        invalidAmount.setAmount_money(money4);

        PaymentRequest zeroAmount = new PaymentRequest();
        zeroAmount.setIdempotency_key(UUID.randomUUID().toString());
        zeroAmount.setPayment_id("tMBhhDtYbWa78YNJoUEnVnqiWqBZY");
        money money5 = new money();
        money5.setAmount(0);
        money5.setCurrency("USD");
        zeroAmount.setAmount_money(money5);

        return new Object[][]{
                {"Create_TC_005 - Full refund", validRefund, 200, "COMPLETED"},
                {"Create_TC_006 - Exceeding amount", overRefund, 400, null},
                {"Create_TC_007 - Missing payment_id", missingPaymentId, 400, null},
                {"Create_TC_008 - Invalid amount type", invalidAmount, 400, null},
                {"Create_TC_009 - Zero amount refund", zeroAmount, 400, null}
        };
    }
}
