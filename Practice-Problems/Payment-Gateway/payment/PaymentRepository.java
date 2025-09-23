package payment;

import model.*;

import java.util.LinkedList;

public class PaymentRepository {
    LinkedList<Payment> payments = new LinkedList<Payment>();
    int currentId = 0;

    public Payment createPayment(PaymentRequest req) {
        Payment payment = new Payment(
            currentId++,
            req.getAmount(),
            req.getCurrency(),
            req.getStrategy()
        );

        payments.add(payment);

        System.out.println(currentId + " " + payments.size());

        return payment;
    }
}
