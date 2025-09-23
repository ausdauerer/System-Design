package payment.strategy;

import model.*;
import provider.*;

public class UPIPaymentStrategy implements IPaymentStrategy {
    public boolean makePayment(IPaymentProvider provider, PaymentRequest pr) {
        return provider.makeUPIPayment(pr);
    }
}
