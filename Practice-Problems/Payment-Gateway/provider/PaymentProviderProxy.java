package provider;

import model.*;

public class PaymentProviderProxy implements IPaymentProvider {
    IPaymentProvider provider;

    public PaymentProviderProxy(IPaymentProvider provider) {
        this.provider = provider;
    }

    public boolean makeDebitCardPayment(PaymentRequest pr) {
        for (int i = 0; i < 3; i++) {
            boolean status = this.provider.makeDebitCardPayment(pr);
            if (status == true) {
                return true;
            }
        }
        
        return false;
    }

    public boolean makeCreditCardPayment(PaymentRequest pr) {
        for (int i = 0; i < 3; i++) {
            boolean status = this.provider.makeCreditCardPayment(pr);
            if (status == true) {
                return true;
            }
        }

        return false;
    }

    public boolean makeUPIPayment(PaymentRequest pr) {
        for (int i = 0; i < 3; i++) {
            boolean status = this.provider.makeUPIPayment(pr);
            if (status == true) {
                return true;
            }
        }

        return false;
    }
}
