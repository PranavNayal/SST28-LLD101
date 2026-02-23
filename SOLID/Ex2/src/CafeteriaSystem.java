import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final FileStore store = new FileStore();
    private final TaxRuleEngine taxEngine;
    private final DiscountCalculator discountCalc;
    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this.taxEngine = new SimpleTaxRuleEngine();
        this.discountCalc = new SimpleDiscountCalculator();
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        // Calculate subtotal
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
        }

        // Calculate tax and discount
        double tax = taxEngine.calculateTax(customerType, subtotal);
        double discount = discountCalc.calculateDiscount(customerType, subtotal, lines.size());
        double taxPct = TaxRules.taxPercent(customerType);

        // Build invoice
        String invoiceContent = new InvoiceBuilder()
                .withInvoiceId(invId)
                .withOrderLines(lines, menu)
                .withTotals(subtotal, taxPct, tax, discount)
                .build();

        // Print and save
        System.out.print(invoiceContent);
        store.save(invId, invoiceContent);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
