package com.example.reports;

/**
 * TODO (student):
 * Implement Proxy responsibilities here:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    // Lazy: null until first authorized display() call
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // 1. Access control check
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[proxy] ACCESS DENIED — " + user.getName()
                    + " (" + user.getRole() + ") cannot view " + classification + " report: " + title);
            return;
        }

        // 2. Lazy-load + cache: only create RealReport on first authorized call
        if (realReport == null) {
            System.out.println("[proxy] Lazy-loading report: " + reportId);
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] Serving cached report: " + reportId);
        }

        // 3. Delegate to real subject
        realReport.display(user);
    }
}
