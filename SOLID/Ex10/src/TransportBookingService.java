/**
 * DIP-compliant: booking service depends only on abstractions.
 * Concretes are injected via constructor; easy to swap for testing.
 */
public class TransportBookingService {
    private final DistanceCalculate dist;
    private final DriverAllocate alloc;
    private final PaymentCharge pay;

    public TransportBookingService(DistanceCalculate dist, DriverAllocate alloc, PaymentCharge pay) {
        this.dist  = dist;
        this.alloc = alloc;
        this.pay   = pay;
    }

    public void book(TripRequest req) {
        double km = dist.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = alloc.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667; // pricing rule
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = pay.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}

