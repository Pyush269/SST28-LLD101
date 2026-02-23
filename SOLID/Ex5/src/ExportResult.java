/**
 * LSP contract: export() always returns a non-null ExportResult.
 * If the export cannot succeed, isError is true and errorMessage describes why.
 * No exporter should throw for a valid (non-null) ExportRequest.
 */
public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean isError;
    public final String errorMessage;

    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.isError = false;
        this.errorMessage = null;
    }

    public static ExportResult error(String message) {
        return new ExportResult(message);
    }

    private ExportResult(String errorMessage) {
        this.contentType = "";
        this.bytes = new byte[0];
        this.isError = true;
        this.errorMessage = errorMessage;
    }
}
