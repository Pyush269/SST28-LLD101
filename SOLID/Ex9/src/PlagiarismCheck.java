/**
 * DIP abstraction: high-level pipeline depends on this, not on the concrete class.
 */
public interface PlagiarismCheck {
    int check(Submission s);
}
