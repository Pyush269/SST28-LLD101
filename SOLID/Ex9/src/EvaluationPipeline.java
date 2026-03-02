/**
 * DIP-compliant: high-level pipeline depends only on abstractions.
 * Concretes are injected from outside (via constructor).
 */
public class EvaluationPipeline {
    private final PlagiarismCheck checker;
    private final CodeGrade grader;
    private final ReportWrite writer;
    private final Rubric rubric;

    public EvaluationPipeline(PlagiarismCheck checker, CodeGrade grader,
                               ReportWrite writer, Rubric rubric) {
        this.checker = checker;
        this.grader  = grader;
        this.writer  = writer;
        this.rubric  = rubric;
    }

    public void evaluate(Submission sub) {
        int plag = checker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = writer.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}

