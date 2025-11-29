package POJOS.CancelPayment;

public class CancelPaymentRequest {
    private String idempotency_key;

    public CancelPaymentRequest(String idempotency_key) {
        this.idempotency_key = idempotency_key;
    }

    public String getIdempotency_key() {
        return idempotency_key;
    }

    public void setIdempotency_key(String idempotency_key) {
        this.idempotency_key = idempotency_key;
    }
}
