package model;

public class PaymentRequest {
    PaymentStrategy ps;
    int amount;
    Currency curr;

    public PaymentRequest(PaymentStrategy ps, int amount, Currency curr) {
        this.ps = ps;
        this.amount = amount;
        this.curr = curr;
    }

    public PaymentStrategy getStrategy() {
        return this.ps;
    }

    public int getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return this.curr;
    }
}
