import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        // Build registry — adding a new room type/add-on only requires a new line here.
        PricingRegistry registry = new PricingRegistry()
                .register(new SingleRoomPricing())
                .register(new DoubleRoomPricing())
                .register(new TripleRoomPricing())
                .register(new DeluxeRoomPricing())
                .register(new MessAddOnPricing())
                .register(new LaundryAddOnPricing())
                .register(new GymAddOnPricing())
                .register(new LateFeeAddOn()); // stretch goal: no changes to calculator

        HostelFeeCalculator calc = new HostelFeeCalculator(registry, new FakeBookingRepo());

        // Case 1: must match README sample output exactly
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        calc.process(req);

        // Stretch goal: late fee add-on registered — calculator loop handles it
        // automatically
        System.out.println();
        BookingRequest req2 = new BookingRequest(LegacyRoomTypes.SINGLE, List.of(AddOn.MESS, AddOn.LATE_FEE));
        calc.process(req2);
    }
}
