package refunds.data;

import POJOS.createPayment.PaymentRequest;
import POJOS.createPayment.money;
import org.testng.annotations.DataProvider;

import java.util.UUID;

public class RefundData {
    @DataProvider(name = "createRefundData")
    public Object[][] createRefundData() {

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

    @DataProvider(name = "getRefundData")
    public Object[][] getRefundData() {
        return new Object[][]{
                {
                        "Create_TC_010 - Retrieve valid refund",
                        "fx1w3eSfPfhHoAqR0dJevcq6K9TZY_87uPs8iKpVIYjTHZ8fhGtEBiREwNUnVktTA9T722L0O", // ✅ Valid refund ID
                        200,
                        true
                },
                {
                        "Create_TC_011 - Retrieve invalid refund",
                        "fx1w3eSfPfhHoAqR0dJevcq6K9TZY_87uPs8iKpVIYjTHZ8fhGtEBiREwNUnVktTA9T722L0o", // ❌ Invalid refund ID (note lowercase 'o' at end)
                        404,
                        false
                }
        };
    }

    @DataProvider(name = "listRefundsData")
    public Object[][] listRefundsData() {
        return new Object[][]{
                {
                        "List_TC_001 - Get all refunds",
                        null,
                        200,
                        true // Expect refunds array
                },
                {
                        "List_TC_002 - Verify refund transitions",
                        null,
                        200,
                        true
                },
                {
                        "List_TC_003 - Filter refunds by date range",
                        "?begin_time=2025-11-06T00:00:00Z&end_time=2025-11-07T23:59:59Z",
                        200,
                        true
                },
                {
                        "List_TC_004 - Invalid query parameter",
                        "?status=INVALID_STATUS",
                        400,
                        false
                }
        };
    }
}
