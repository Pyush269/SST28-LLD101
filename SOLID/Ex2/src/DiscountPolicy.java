// Abstraction for discount computation. CafeteriaSystem depends on this, not on DiscountRules directly.
public interface DiscountPolicy {
    double discountAmount(String customerType, double subtotal, int distinctLines);
}
