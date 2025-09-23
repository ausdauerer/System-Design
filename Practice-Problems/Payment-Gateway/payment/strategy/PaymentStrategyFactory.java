package payment.strategy;

import model.*;
import provider.*;

public class PaymentStrategyFactory {
    static PaymentStrategyFactory instance = null;

    public static PaymentStrategyFactory getInstance() {
        if (instance == null) {
            instance = new PaymentStrategyFactory();
        }

        return instance;
    }

    public IPaymentStrategy getStrategy(PaymentStrategy ps) throws Exception {
        IPaymentStrategy strategy;

        switch(ps) {
            case PaymentStrategy.CREDIT_CARD:
                strategy = new CreditCardPaymentStrategy();
            break;
            case PaymentStrategy.DEBIT_CARD:
                strategy = new DebitCardPaymentStrategy();
            break;
            case PaymentStrategy.UPI:
                strategy = new UPIPaymentStrategy();
            break;
            default:
                throw new Exception("Payment strategy not found");
        }
        
        return strategy;
    }
}
