// Abstraction for tax computation. CafeteriaSystem depends on this, not on TaxRules directly.
public interface TaxPolicy {
    double taxPercent(String customerType);
}
