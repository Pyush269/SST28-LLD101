/**
 * DIP abstraction: high-level pipeline depends on this, not on the concrete class.
 */
public interface ReportWrite {
    String write(Submission s, int plag, int code);
}
