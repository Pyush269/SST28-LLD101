public class GymAddOnPricing implements AddOnPricing {
    @Override public AddOn addOn() { return AddOn.GYM; }
    @Override public double cost() { return 300.0; }
}
