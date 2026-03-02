/**
 * DIP abstraction: high-level pipeline depends on this, not on the concrete class.
 */
public interface CodeGrade {
    int grade(Submission s, Rubric r);
}
