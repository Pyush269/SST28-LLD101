public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        // LSP: no try-catch needed — all exporters return ExportResult consistently.
        System.out.println("PDF: " + describe(pdf.export(req)));
        System.out.println("CSV: " + describe(csv.export(req)));
        System.out.println("JSON: " + describe(json.export(req)));

        // Stretch goal: add new exporter without changing existing exporters
        System.out.println();
        Exporter xml = new XmlExporter();
        System.out.println("XML: " + describe(xml.export(req)));
    }

    private static String describe(ExportResult out) {
        if (out.isError)
            return "ERROR: " + out.errorMessage;
        return "OK bytes=" + out.bytes.length;
    }
}
