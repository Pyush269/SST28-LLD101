// Abstraction for booking persistence. HostelFeeCalculator depends on this, not on FakeBookingRepo.
public interface BookingStore {
    void save(String id, BookingRequest req, Money monthly, Money deposit);
}
