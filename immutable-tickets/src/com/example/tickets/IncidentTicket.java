package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable incident ticket. Created exclusively via Builder.
 * No setters; fields are private final; tags list is defensively copied.
 */
public final class IncidentTicket {

    // Required fields
    private final String id;
    private final String reporterEmail;
    private final String title;

    // Optional fields
    private final String description;
    private final String priority;        // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;      // defensive copy — immutable view
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;

    /** Private constructor — only Builder may call this. */
    private IncidentTicket(Builder b) {
        this.id              = b.id;
        this.reporterEmail   = b.reporterEmail;
        this.title           = b.title;
        this.description     = b.description;
        this.priority        = b.priority;
        this.tags            = Collections.unmodifiableList(new ArrayList<>(b.tags));
        this.assigneeEmail   = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes      = b.slaMinutes;
        this.source          = b.source;
    }

    // --- Safe getters (no state leakage) ---
    public String getId()              { return id; }
    public String getReporterEmail()   { return reporterEmail; }
    public String getTitle()           { return title; }
    public String getDescription()     { return description; }
    public String getPriority()        { return priority; }
    public List<String> getTags()      { return tags; } // unmodifiable view
    public String getAssigneeEmail()   { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes()     { return slaMinutes; }
    public String getSource()          { return source; }

    /** Returns a Builder pre-populated with this ticket's values (for creating modified copies). */
    public Builder toBuilder() {
        return new Builder(id, reporterEmail, title)
                .description(description)
                .priority(priority)
                .tags(new ArrayList<>(tags))
                .assigneeEmail(assigneeEmail)
                .customerVisible(customerVisible)
                .slaMinutes(slaMinutes)
                .source(source);
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    // =========================================================================
    // Builder
    // =========================================================================
    public static final class Builder {

        // Required
        private final String id;
        private final String reporterEmail;
        private final String title;

        // Optional (with defaults)
        private String description;
        private String priority     = "MEDIUM";
        private List<String> tags   = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible = false;
        private Integer slaMinutes;
        private String source;

        public Builder(String id, String reporterEmail, String title) {
            this.id            = id;
            this.reporterEmail = reporterEmail;
            this.title         = title;
        }

        public Builder description(String description)          { this.description = description;       return this; }
        public Builder priority(String priority)                { this.priority = priority;             return this; }
        public Builder tags(List<String> tags)                  { this.tags = tags != null ? tags : new ArrayList<>(); return this; }
        public Builder addTag(String tag)                       { this.tags.add(tag);                  return this; }
        public Builder assigneeEmail(String assigneeEmail)      { this.assigneeEmail = assigneeEmail;   return this; }
        public Builder customerVisible(boolean customerVisible) { this.customerVisible = customerVisible; return this; }
        public Builder slaMinutes(Integer slaMinutes)           { this.slaMinutes = slaMinutes;         return this; }
        public Builder source(String source)                    { this.source = source;                 return this; }

        /** Centralized validation — all checks live here, nowhere else. */
        public IncidentTicket build() {
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            if (assigneeEmail != null) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            return new IncidentTicket(this);
        }
    }
}

