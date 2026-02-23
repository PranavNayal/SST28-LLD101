public class GymAddOn implements AddOnCharge {
    @Override
    public Money getCost() {
        return new Money(300.0);
    }
}
