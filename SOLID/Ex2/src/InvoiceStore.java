// Abstraction for invoice persistence. CafeteriaSystem depends on this, not on FileStore directly.
public interface InvoiceStore {
    void save(String id, String content);
    int countLines(String id);
}
