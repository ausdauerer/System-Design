package provider;

import model.*;

public interface IPaymentProvider {
    public boolean makeDebitCardPayment(PaymentRequest pr);
    public boolean makeCreditCardPayment(PaymentRequest pr);
    public boolean makeUPIPayment(PaymentRequest pr);
}
