import java.nio.charset.StandardCharsets;

// Stretch goal: new exporter added without changing any existing exporter or base class.
// Honors the LSP contract: never throws, returns ExportResult consistently.
public class XmlExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        String body = req.body == null ? "" : xmlEscape(req.body);
        String title = req.title == null ? "" : xmlEscape(req.title);
        String xml = "<report><title>" + title + "</title><body>" + body + "</body></report>";
        return new ExportResult("application/xml", xml.getBytes(StandardCharsets.UTF_8));
    }

    private String xmlEscape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
