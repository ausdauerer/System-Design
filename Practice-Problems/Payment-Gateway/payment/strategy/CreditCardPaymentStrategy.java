package payment.strategy;

import model.*;
import provider.*;

public class CreditCardPaymentStrategy implements IPaymentStrategy {
    public boolean makePayment(IPaymentProvider provider, PaymentRequest pr) {
        return provider.makeCreditCardPayment(pr);
    }
}
