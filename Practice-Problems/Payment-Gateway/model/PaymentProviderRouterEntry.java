package model;

import java.util.Objects;

public class PaymentProviderRouterEntry implements Comparable<PaymentProviderRouterEntry> {
    public PaymentProvider provider;
    public int successRate;

    public PaymentProviderRouterEntry(PaymentProvider provider, int successRate) {
        this.provider = provider;
        this.successRate = successRate;
    }

    @Override
    public int compareTo(PaymentProviderRouterEntry e) {
        return -this.successRate + e.successRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentProviderRouterEntry)) return false;
        PaymentProviderRouterEntry entry = (PaymentProviderRouterEntry) o;
        return entry.provider == this.provider;
    }

    @Override
    public int hashCode() {
        return Objects.hash(provider);
    }
}