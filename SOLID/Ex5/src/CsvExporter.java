import java.nio.charset.StandardCharsets;

// LSP: properly quotes CSV fields instead of lossy stripping — preserves data semantics.
public class CsvExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        String body = req.body == null ? "" : csvQuote(req.body);
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * RFC 4180: wrap in double-quotes if the field contains comma, newline, or
     * quote.
     */
    private String csvQuote(String s) {
        if (s.contains(",") || s.contains("\n") || s.contains("\"")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }
}
