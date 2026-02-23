public class SimpleTaxRuleEngine implements TaxRuleEngine {
    @Override
    public double calculateTax(String customerType, double subtotal) {
        double taxPct = TaxRules.taxPercent(customerType);
        return subtotal * (taxPct / 100.0);
    }
}
