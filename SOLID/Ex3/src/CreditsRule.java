import java.util.Optional;

// Stretch goal: threshold is configurable via RuleInput — rule logic never needs to change.
public class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(RuleInput cfg) { this.minCredits = cfg.minCredits; }
    public CreditsRule() { this(new RuleInput()); }

    @Override
    public Optional<String> check(StudentProfile s) {
        if (s.earnedCredits < minCredits) return Optional.of("credits below " + minCredits);
        return Optional.empty();
    }
}

