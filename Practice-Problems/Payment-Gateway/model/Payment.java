package model;

public class Payment {
    int id;
    int amount;
    Currency curr;
    PaymentStatus status;
    PaymentStrategy strategy;

    public Payment(int id, int amount, Currency curr, PaymentStrategy strategy) {
        this.id = id;
        this.amount = amount;
        this.curr = curr;
        this.strategy = this.strategy;
        this.status = PaymentStatus.PENDING;
    }

    public void updateStatus(PaymentStatus status) {
        this.status = status;
    }
}
