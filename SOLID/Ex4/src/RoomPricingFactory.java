public class RoomPricingFactory {
    public static RoomPricingStrategy createStrategy(int roomType) {
        return switch (roomType) {
            case LegacyRoomTypes.SINGLE -> new SingleRoomPricing();
            case LegacyRoomTypes.DOUBLE -> new DoubleRoomPricing();
            case LegacyRoomTypes.TRIPLE -> new TripleRoomPricing();
            default -> new DeluxeRoomPricing();
        };
    }
}
