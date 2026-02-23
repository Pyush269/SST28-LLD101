// Stretch goal: late fee add-on registered in PricingRegistry — HostelFeeCalculator needs zero changes.
public class LateFeeAddOn implements AddOnPricing {
    @Override public AddOn addOn() { return AddOn.LATE_FEE; }
    @Override public double cost() { return 200.0; }
}
