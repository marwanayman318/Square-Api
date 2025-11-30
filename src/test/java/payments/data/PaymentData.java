package payments.data;

import POJOS.CancelPayment.CancelPaymentRequest;
import POJOS.UpdatePayment.UpdatePayment;
import org.testng.annotations.DataProvider;

import java.util.UUID;

import static Helper.CreatePaymentHelper.createPayment;

public class PaymentData {
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

    @DataProvider(name = "listPaymentsData")
    public Object[][] listPaymentsData() {
        return new Object[][]{
                {"valid", 5, null, 200, "List payments with valid limit"},
                {"limit", 150, null, 200, "List payments exceeding limit"},
                {"limit", 0, null, 400, "limit must be positive"},
                {"begin_time", "2025-11-05T17:00:05.246Z", null, 200, "Payments should be newer than begin_time"},
                {"begin_time", "invalid-date", null, 400, "Invalid begin_time format"},
                {"end_time", "2025-11-04T17:00:05.246Z", null, 200, "List with end_time"},
                {"sort_order", "ASC", null, 200, "Sort ascending"},
                {"cursor", "eyJjcmVhdGVkQXQiOjE3NjIzNjAyMjIzMjcsImlkIjoiM0hPOE9qYjVWTE9YQWtpS25lWjNrdDdVcWRaWlkifQ", 1, 200, "Pagination with cursor"}
        };
    }

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

    @DataProvider(name = "getPaymentsData")
    public Object[][] getPaymentsData() {
        return new Object[][]{
                {"Get_TC_016 - Valid Payment ID", "7RW8DEuEAVCBSiupp5go5MGjdWHZY", 200, true},
                {"Get_TC_017 - Invalid Payment ID", "7RW8DEuEAVCBSiupp5go5MGjdWHZy", 404, false}
        };
    }

    @DataProvider(name = "PaymentsData")
    public Object[][] createData() {
        return new Object[][]{
                {2500, "USD", "cnon:card-nonce-ok", true, 200},
                {0, "USD", "cnon:card-nonce-ok", true, 400},
                {1500, "EGG", "cnon:card-nonce-ok", true, 400},
                {1200, "USD", "cnon:card-nonce-ok", false, 200},
                {-2500, "USD", "cnon:card-nonce-ok", true, 400}
        };
    }

    @DataProvider(name = "completePaymentData")
    public Object[][] completePaymentData() {
        return new Object[][]{
                {
                        "Complete_TC_022 - Complete Approved Payment",
                        "f7MhX4mPdLwbF2vibm6Fm0RsbFAZY",
                        new CancelPaymentRequest(UUID.randomUUID().toString()),
                        200,
                        "COMPLETED"
                },
                {
                        "Complete_TC_023 - Complete Canceled Payment",
                        "VW07oXYkp7NcZ76xyCB2MphAhnRZY",
                        new CancelPaymentRequest(UUID.randomUUID().toString()),
                        400,
                        "CANCELED"
                }
        };
    }

    @DataProvider(name = "CancelPaymentData")
    public Object[][] cancelPaymentData() {
        return new Object[][]{
                {"Cancel_TC_015", new CancelPaymentRequest("vnmrGSCPp0RC6GQFar0LocVWUpJZY"), 200, null}
        };
    }
}
