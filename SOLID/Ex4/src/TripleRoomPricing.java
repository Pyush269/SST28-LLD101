public class TripleRoomPricing implements RoomPricing {
    @Override public int roomType() { return LegacyRoomTypes.TRIPLE; }
    @Override public double monthlyBase() { return 12000.0; }
}
