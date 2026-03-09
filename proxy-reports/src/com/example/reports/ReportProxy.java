package com.example.reports;

/**
 * Proxy: Controls access and implements lazy loading of the RealReport.
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    private RealReport realReport; // Lazily loaded and cached

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // Access control check
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: " + user.getName() + " cannot access " + classification + " report");
            return;
        }

        // Lazy loading: only create RealReport on first access
        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }

        realReport.display(user);
    }
}
