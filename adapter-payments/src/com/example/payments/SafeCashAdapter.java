package com.example.payments;

import java.util.Objects;

/**
 * Adapter: bridges SafeCashClient (incompatible SDK) to the PaymentGateway target interface.
 * SafeCashClient.createPayment(amount, user).confirm() → PaymentGateway.charge(customerId, amountCents)
 */
public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}
