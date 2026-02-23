public class DeluxeRoomPricing implements RoomPricing {
    @Override public int roomType() { return LegacyRoomTypes.DELUXE; }
    @Override public double monthlyBase() { return 16000.0; }
}
