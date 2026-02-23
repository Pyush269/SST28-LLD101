public class DoubleRoomPricing implements RoomPricing {
    @Override public int roomType() { return LegacyRoomTypes.DOUBLE; }
    @Override public double monthlyBase() { return 15000.0; }
}
