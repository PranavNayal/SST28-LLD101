public class AddOnChargeFactory {
    public static AddOnCharge createCharge(AddOn addOn) {
        return switch (addOn) {
            case MESS -> new MessAddOn();
            case LAUNDRY -> new LaundryAddOn();
            case GYM -> new GymAddOn();
        };
    }
}
