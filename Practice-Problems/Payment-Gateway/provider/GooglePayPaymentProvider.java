package provider;

import model.*;

public class GooglePayPaymentProvider {
    public boolean makeGooglePayCreditCardPayment(PaymentRequest pr) {
        System.out.println("Gpay successfully made the credit card payment for " +  pr.getAmount() + " " + pr.getCurrency());
        return true;
    }

    public boolean makeGooglePayUPIPayment(PaymentRequest pr) {
        System.out.println("Gpay successfully made the UPI payment for " +  pr.getAmount() + " " + pr.getCurrency());
        return true;
    }

    public boolean makeGooglePayDebitCardPayment(PaymentRequest pr) {
        System.out.println("Gpay successfully made the debit card payment for " +  pr.getAmount() + " " + pr.getCurrency());
        return true;
    }
}
