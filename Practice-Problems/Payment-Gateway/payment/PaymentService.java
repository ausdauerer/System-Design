package payment;

import provider.*;
import model.*;
import payment.strategy.*;

public class PaymentService {
    PaymentProviderRouter router;
    PaymentProviderFactory paymentProviderFactory;
    PaymentStrategyFactory paymentStrategyFactory;
    PaymentRepository repository;

    public PaymentService(PaymentProviderRouter router, PaymentProviderFactory paymentProviderFactory, PaymentStrategyFactory paymentStrategyFactory, PaymentRepository repository) {
        this.router = router;
        this.paymentProviderFactory = paymentProviderFactory;
        this.paymentStrategyFactory = paymentStrategyFactory;
        this.repository = repository;
    }

    public void makePayment(PaymentRequest pr) throws Exception {
        PaymentProvider providerName = this.router.getProvider(pr.getStrategy());

        IPaymentProvider provider = this.paymentProviderFactory.getProvider(providerName);

        IPaymentStrategy strategy = this.paymentStrategyFactory.getStrategy(pr.getStrategy());

        Payment payment = this.repository.createPayment(pr);

        boolean success = strategy.makePayment(provider, pr);

        if (success == true) {
            payment.updateStatus(PaymentStatus.SUCCESSFUL);
        } else {
            payment.updateStatus(PaymentStatus.FAILED);
        }

        
    }
}
