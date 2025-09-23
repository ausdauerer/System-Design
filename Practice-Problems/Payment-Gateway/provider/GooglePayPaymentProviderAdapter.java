package provider;

import model.*;

public class GooglePayPaymentProviderAdapter implements IPaymentProvider {
    GooglePayPaymentProvider provider;

    public GooglePayPaymentProviderAdapter(GooglePayPaymentProvider provider) {
        this.provider = provider;
    }

    public boolean makeDebitCardPayment(PaymentRequest pr) {
        return this.provider.makeGooglePayDebitCardPayment(pr);
    }

    public boolean makeCreditCardPayment(PaymentRequest pr) {
        return this.provider.makeGooglePayCreditCardPayment(pr);
    }

    public boolean makeUPIPayment(PaymentRequest pr) {
        return this.provider.makeGooglePayUPIPayment(pr);
    }
}
