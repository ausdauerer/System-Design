package provider;

import model.*;

public class PaymentProviderFactory {
    static PaymentProviderFactory instance = null;

    public static PaymentProviderFactory getInstance() {
        if (instance == null) {
            instance = new PaymentProviderFactory();
        }

        return instance;
    }

    public IPaymentProvider getProvider(PaymentProvider type) throws Exception{
        IPaymentProvider provider;

        switch(type) {
            case PaymentProvider.CRED:
                provider = new PaymentProviderProxy(new CredPaymentProviderAdapter(new CredPaymentProvider()));
            break;
            case PaymentProvider.GOOGLE_PAY:
                provider = new PaymentProviderProxy(new GooglePayPaymentProviderAdapter(new GooglePayPaymentProvider()));
            break;
            default:
                throw new Exception("Provider not found");
        }

        return provider;
    }
}
