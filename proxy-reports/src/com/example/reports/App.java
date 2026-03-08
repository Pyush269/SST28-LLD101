package com.example.reports;

/**
 * Starter demo.
 *
 * CURRENT BEHAVIOR:
 * - Everyone can open everything
 * - Disk load happens on every call
 *
 * AFTER REFACTOR:
 * - Client code should use ReportProxy
 * - Unauthorized access should be blocked
 * - Real report should load lazily and ideally once per proxy
 */
public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        // Clients use Report interface; actual object is a ReportProxy
        Report publicReport = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review", "FACULTY");
        Report adminReport = new ReportProxy("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        // Student opens public report — allowed, lazy-loads
        viewer.open(publicReport, student);
        System.out.println();

        // Student tries faculty report — DENIED (no disk load)
        viewer.open(facultyReport, student);
        System.out.println();

        // Faculty opens faculty report — allowed, lazy-loads
        viewer.open(facultyReport, faculty);
        System.out.println();

        // Admin opens admin report — allowed, lazy-loads
        viewer.open(adminReport, admin);
        System.out.println();

        // Admin opens admin report again — served from cache (no disk load)
        viewer.open(adminReport, admin);
    }
}
