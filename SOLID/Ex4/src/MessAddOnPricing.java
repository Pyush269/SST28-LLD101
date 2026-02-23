public class MessAddOnPricing implements AddOnPricing {
    @Override public AddOn addOn() { return AddOn.MESS; }
    @Override public double cost() { return 500.0; }
}

