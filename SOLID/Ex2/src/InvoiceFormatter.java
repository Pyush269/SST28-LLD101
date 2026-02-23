import java.util.List;

// Single responsibility: build the printable invoice string from computed values.
public class InvoiceFormatter {
    public String format(String invoiceId, List<String> lineDescriptions,
                         double subtotal, double taxPct, double tax,
                         double discount, double total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(invoiceId).append("\n");
        for (String line : lineDescriptions) sb.append(line).append("\n");
        sb.append(String.format("Subtotal: %.2f\n", subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        sb.append(String.format("Discount: -%.2f\n", discount));
        sb.append(String.format("TOTAL: %.2f\n", total));
        return sb.toString();
    }
}
