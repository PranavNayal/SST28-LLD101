public class LaundryAddOn implements AddOnCharge {
    @Override
    public Money getCost() {
        return new Money(500.0);
    }
}
