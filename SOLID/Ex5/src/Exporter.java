/**
 * LSP base contract:
 * - Precondition: req must be non-null (enforced here, not by subtypes).
 * - Postcondition: always returns a non-null ExportResult (never throws for valid input).
 * - If a format has limitations, it returns ExportResult.error(...) instead of throwing.
 */
public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {
        if (req == null) throw new NullPointerException("ExportRequest must not be null");
        return doExport(req);
    }

    /** Subtypes implement format-specific encoding here. req is guaranteed non-null. */
    protected abstract ExportResult doExport(ExportRequest req);
}

