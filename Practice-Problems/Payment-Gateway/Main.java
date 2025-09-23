import payment.*;
import model.*;
import provider.*;

import payment.strategy.*;

public class Main {
    public static void main(String[] args) throws Exception {
        PaymentProviderRouter router = new PaymentProviderRouter();

        router.addProvider(PaymentStrategy.UPI, PaymentProvider.CRED);
        router.addProvider(PaymentStrategy.CREDIT_CARD, PaymentProvider.CRED);
        router.addProvider(PaymentStrategy.DEBIT_CARD, PaymentProvider.CRED);
        router.addProvider(PaymentStrategy.UPI, PaymentProvider.GOOGLE_PAY);

        PaymentService service = new PaymentService(router, new PaymentProviderFactory(), new PaymentStrategyFactory(), new PaymentRepository());

        PaymentRequest req = new PaymentRequest(PaymentStrategy.UPI, 100, Currency.INR);

        service.makePayment(req);

        router.updateProvider(PaymentStrategy.UPI, PaymentProvider.CRED, 70);

        PaymentRequest req2 = new PaymentRequest(PaymentStrategy.UPI, 120, Currency.INR);

        service.makePayment(req2);
    }
}
