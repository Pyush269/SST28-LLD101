import java.util.*;

// Single responsibility: orchestrate the eligibility workflow.
// OCP: new rules are added by extending the rules list, not by modifying this class.
public class EligibilityEngine {
    private final EligibilityStore store;
    private final List<EligibilityRule> rules;
    private final ReportPrinter printer;

    public EligibilityEngine(EligibilityStore store, List<EligibilityRule> rules, ReportPrinter printer) {
        this.store = store;
        this.rules = rules;
        this.printer = printer;
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityEngineResult r = evaluate(s);
        printer.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();

        // OCP: iterate rules — adding a new rule never requires editing this method.
        for (EligibilityRule rule : rules) {
            Optional<String> failure = rule.check(s);
            if (failure.isPresent()) {
                reasons.add(failure.get());
                return new EligibilityEngineResult("NOT_ELIGIBLE", reasons);
            }
        }

        return new EligibilityEngineResult("ELIGIBLE", reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}

