package com.example.reports;

/**
 * Viewer that works with the Report interface (Proxy or any implementation).
 * Now properly supports access control and lazy loading through the Proxy.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
