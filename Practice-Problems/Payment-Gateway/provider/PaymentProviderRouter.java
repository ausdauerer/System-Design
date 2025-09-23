package provider;

import model.*;

import java.util.HashMap;
import java.util.PriorityQueue;

public class PaymentProviderRouter {
    HashMap<PaymentStrategy, PriorityQueue<PaymentProviderRouterEntry>> providers = new HashMap<PaymentStrategy, PriorityQueue<PaymentProviderRouterEntry>>();

    public void addProvider(PaymentStrategy ps, PaymentProvider provider) {
        if (!this.providers.containsKey(ps)) {
            this.providers.put(ps, new PriorityQueue<PaymentProviderRouterEntry>());
        }

        PaymentProviderRouterEntry entry = new PaymentProviderRouterEntry(provider, 100);

        this.providers.get(ps).add(entry);
    }

    public void updateProvider(PaymentStrategy ps, PaymentProvider provider, int successRate) {
        if (this.providers.containsKey(ps)) {
            PaymentProviderRouterEntry entry = new PaymentProviderRouterEntry(provider, successRate);
            this.providers.get(ps).remove(entry);
            this.providers.get(ps).add(entry);
        }
    }

    public void removeProvider(PaymentStrategy ps, PaymentProvider provider) {
        if (this.providers.containsKey(ps)) {
            PaymentProviderRouterEntry entry = new PaymentProviderRouterEntry(provider, 0);
            this.providers.get(ps).remove(entry);
        }
    }

    public PaymentProvider getProvider(PaymentStrategy ps) {
        return this.providers.get(ps).peek().provider;
    }
}
