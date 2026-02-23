import java.util.Optional;

// Stretch goal: threshold is configurable via RuleInput — rule logic never needs to change.
public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(RuleInput cfg) { this.minAttendance = cfg.minAttendance; }
    public AttendanceRule() { this(new RuleInput()); }

    @Override
    public Optional<String> check(StudentProfile s) {
        if (s.attendancePct < minAttendance) return Optional.of("attendance below " + minAttendance);
        return Optional.empty();
    }
}

