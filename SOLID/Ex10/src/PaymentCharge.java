/**
 * DIP abstraction: booking service depends on this, not on PaymentGateway directly.
 */
public interface PaymentCharge {
    String charge(String studentId, double amount);
}
