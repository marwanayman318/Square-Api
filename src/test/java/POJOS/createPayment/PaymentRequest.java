package POJOS.createPayment;

import java.util.UUID;

public class PaymentRequest {
    private String idempotency_key;
    private money amount_money;
    private String source_id;
    private String payment_id;
    private boolean autocomplete;

    public PaymentRequest() {}

    public PaymentRequest(String idempotencyKey, String sourceId, money amountMoney, boolean autocomplete) {
        this.idempotency_key = idempotencyKey;
        this.source_id = sourceId;
        this.amount_money = amountMoney;
        this.autocomplete = autocomplete;
    }

    public String getIdempotency_key() {
        return idempotency_key;
    }

    public void setIdempotency_key(String idempotency_key) {

        this.idempotency_key = idempotency_key;
    }

    public money getAmount_money() {

        return amount_money;
    }

    public void setAmount_money(money amount_money) {

        this.amount_money = amount_money;
    }

    public String getSource_id() {

        return source_id;
    }

    public void setSource_id(String source_id) {

        this.source_id = source_id;
    }
    public boolean isAutocomplete() {
        return autocomplete;
    }

    public void setAutocomplete(boolean autocomplete) {
        this.autocomplete = autocomplete;
    }

    public String getPayment_id() {
        return payment_id;
    }
    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }
}
