public class SimpleDiscountCalculator implements DiscountCalculator {
    @Override
    public double calculateDiscount(String customerType, double subtotal, int distinctLines) {
        return DiscountRules.discountAmount(customerType, subtotal, distinctLines);
    }
}
