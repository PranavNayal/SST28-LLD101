public class MessAddOn implements AddOnCharge {
    @Override
    public Money getCost() {
        return new Money(1000.0);
    }
}
