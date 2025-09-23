package provider;

import model.*;

public class CredPaymentProviderAdapter implements IPaymentProvider{
    CredPaymentProvider provider;

    public CredPaymentProviderAdapter(CredPaymentProvider provider) {
        this.provider = provider;
    }

    public boolean makeDebitCardPayment(PaymentRequest pr) {
        return this.provider.makeCredDebitCardPayment(pr);
    }

    public boolean makeCreditCardPayment(PaymentRequest pr) {
        return this.provider.makeCredCreditCardPayment(pr);
    }

    public boolean makeUPIPayment(PaymentRequest pr) {
        return this.provider.makeCredUPIPayment(pr);
    }
}
