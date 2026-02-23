// OCP: no switch/if-else. New room types and add-ons are registered in PricingRegistry — this class never changes.
public class HostelFeeCalculator {
    private static final Money DEPOSIT = new Money(5000.00);
    private static final String BOOKING_PREFIX = "H-7781"; // deterministic for test reproducibility

    private final PricingRegistry registry;
    private final BookingStore store;

    public HostelFeeCalculator(PricingRegistry registry, BookingStore store) {
        this.registry = registry;
        this.store = store;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        ReceiptPrinter.print(req, monthly, DEPOSIT);
        store.save(BOOKING_PREFIX, req, monthly, DEPOSIT);
    }

    private Money calculateMonthly(BookingRequest req) {
        double total = registry.roomBase(req.roomType);
        for (AddOn a : req.addOns) {
            total += registry.addOnCost(a);
        }
        return new Money(total);
    }
}

