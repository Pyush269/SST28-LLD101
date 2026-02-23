public class LaundryAddOnPricing implements AddOnPricing {
    @Override public AddOn addOn() { return AddOn.LAUNDRY; }
    @Override public double cost() { return 500.0; }
}
