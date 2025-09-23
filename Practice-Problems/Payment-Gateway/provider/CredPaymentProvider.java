package provider;

import model.*;

public class CredPaymentProvider {
    public boolean makeCredCreditCardPayment(PaymentRequest pr) {
        System.out.println("Cred successfully made the credit card payment for " +  pr.getAmount() + " " + pr.getCurrency());
        return true;
    }

    public boolean makeCredUPIPayment(PaymentRequest pr) {
        System.out.println("Cred successfully made the UPI payment for " +  pr.getAmount() + " " + pr.getCurrency());
        return true;
    }

    public boolean makeCredDebitCardPayment(PaymentRequest pr) {
        System.out.println("Cred successfully made the debit card payment for " +  pr.getAmount() + " " + pr.getCurrency());
        return true;
    }
}
