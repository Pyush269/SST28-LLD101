// OCP: add a new room type by implementing this — HostelFeeCalculator never needs to change.
public interface RoomPricing {
    int roomType();          // matches LegacyRoomTypes constant
    double monthlyBase();
}
