package POJOS.UpdatePayment;

import POJOS.createPayment.money;

public class UpdatePayment {
    private String idempotency_key;
    private Payment payment;

    public UpdatePayment(String idempotency_key, int amount, String currency) {
        this.idempotency_key = idempotency_key;
        this.payment = new Payment(amount, currency);
    }

    public String getIdempotency_key() {
        return idempotency_key;
    }

    public Payment getPayment() {
        return payment;
    }

    public static class Payment {
        private money amount_money;

        public Payment(int amount, String currency) {
            this.amount_money = new money(amount, currency);
        }

        public money getAmount_money() {
            return amount_money;
        }
    }
}
