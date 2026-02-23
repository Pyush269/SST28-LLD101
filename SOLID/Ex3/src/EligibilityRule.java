import java.util.Optional;

// OCP: add a new eligibility rule by implementing this interface — EligibilityEngine never needs to change.
public interface EligibilityRule {
    /** Returns the failure reason if the student fails this rule, or empty if they pass. */
    Optional<String> check(StudentProfile s);
}
