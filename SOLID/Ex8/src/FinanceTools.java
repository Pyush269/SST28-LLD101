/**
 * ISP: Finance capability — used only by TreasurerTool.
 */
public interface FinanceTools {
    void addIncome(double amt, String note);
    void addExpense(double amt, String note);
}
