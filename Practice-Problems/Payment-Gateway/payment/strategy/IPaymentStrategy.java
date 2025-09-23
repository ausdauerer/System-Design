package payment.strategy;

import model.*;
import provider.*;

public interface IPaymentStrategy {
    public boolean makePayment(IPaymentProvider provider, PaymentRequest pr);
}
