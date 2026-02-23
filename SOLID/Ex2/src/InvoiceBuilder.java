import java.util.*;

public class InvoiceBuilder {
    private StringBuilder content = new StringBuilder();

    public InvoiceBuilder withInvoiceId(String invId) {
        content.append("Invoice# ").append(invId).append("\n");
        return this;
    }

    public InvoiceBuilder withOrderLines(List<OrderLine> lines, Map<String, MenuItem> menu) {
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            content.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }
        return this;
    }

    public InvoiceBuilder withTotals(double subtotal, double taxPct, double tax, double discount) {
        double total = subtotal + tax - discount;
        content.append(String.format("Subtotal: %.2f\n", subtotal));
        content.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        content.append(String.format("Discount: -%.2f\n", discount));
        content.append(String.format("TOTAL: %.2f\n", total));
        return this;
    }

    public String build() {
        return content.toString();
    }
}
