package com.example.tickets;

/**
 * Service layer that creates and "updates" tickets.
 * Uses Builder to create fully-formed tickets atomically.
 * "Updates" always return a NEW immutable ticket instance via toBuilder().
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // All validation happens inside Builder.build() — no duplication here
        return new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    /**
     * Returns a NEW ticket escalated to CRITICAL — does not mutate the original.
     */
    public IncidentTicket escalateToCritical(IncidentTicket t) {
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    /**
     * Returns a NEW ticket with the assignee set — does not mutate the original.
     */
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
