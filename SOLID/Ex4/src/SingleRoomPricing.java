public class SingleRoomPricing implements RoomPricing {
    @Override public int roomType() { return LegacyRoomTypes.SINGLE; }
    @Override public double monthlyBase() { return 14000.0; }
}
