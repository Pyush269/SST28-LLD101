import java.util.Optional;

// Stretch goal: threshold is configurable via RuleInput — rule logic never needs to change.
public class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(RuleInput cfg) { this.minCgr = cfg.minCgr; }
    public CgrRule() { this(new RuleInput()); }

    @Override
    public Optional<String> check(StudentProfile s) {
        if (s.cgr < minCgr) return Optional.of("CGR below " + minCgr);
        return Optional.empty();
    }
}

