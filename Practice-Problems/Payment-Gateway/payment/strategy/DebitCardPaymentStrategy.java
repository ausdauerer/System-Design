package payment.strategy;

import model.*;
import provider.*;

public class DebitCardPaymentStrategy implements IPaymentStrategy{
    public boolean makePayment(IPaymentProvider provider, PaymentRequest pr) {
        return provider.makeDebitCardPayment(pr);
    }
}
