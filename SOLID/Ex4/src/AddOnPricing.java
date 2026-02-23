// OCP: add a new add-on by implementing this — HostelFeeCalculator never needs to change.
public interface AddOnPricing {
    AddOn addOn();
    double cost();
}
