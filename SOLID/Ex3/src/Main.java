import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");

        // Default thresholds: CGR≥8.0, attendance≥75, credits≥20
        RuleInput cfg = new RuleInput();

        EligibilityEngine engine = new EligibilityEngine(
                new FakeEligibilityStore(),
                List.of(
                    new DisciplinaryRule(),
                    new CgrRule(cfg),
                    new AttendanceRule(cfg),
                    new CreditsRule(cfg)
                ),
                new ReportPrinter()
        );

        // Case 1: fails attendance (must match sample output exactly)
        StudentProfile s1 = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        engine.runAndPrint(s1);

        // Stretch goal: eligible student passes all rules
        System.out.println();
        StudentProfile s2 = new StudentProfile("23BCS1002", "Priya", 8.50, 80, 22, LegacyFlags.NONE);
        engine.runAndPrint(s2);
    }
}

