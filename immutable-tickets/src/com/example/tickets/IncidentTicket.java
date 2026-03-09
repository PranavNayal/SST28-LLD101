package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable ticket object enforcing validation and preventing external mutations.
 */
public class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;     // immutable copy
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;    // optional
    private final String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    private IncidentTicket(Builder b) {
        this.id = b.id;
        this.reporterEmail = b.reporterEmail;
        this.title = b.title;
        this.description = b.description;
        this.priority = b.priority;
        this.tags = Collections.unmodifiableList(new ArrayList<>(b.tags));
        this.assigneeEmail = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes = b.slaMinutes;
        this.source = b.source;
    }

    // Getters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public List<String> getTags() { return tags; }
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    public static Builder builder(String id, String reporterEmail, String title) {
        return new Builder(id, reporterEmail, title);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private final String id;
        private final String reporterEmail;
        private final String title;
        private String description;
        private String priority = "MEDIUM";
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible = false;
        private Integer slaMinutes;
        private String source = "CLI";

        public Builder(String id, String reporterEmail, String title) {
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        public Builder(IncidentTicket t) {
            this.id = t.id;
            this.reporterEmail = t.reporterEmail;
            this.title = t.title;
            this.description = t.description;
            this.priority = t.priority;
            this.tags = new ArrayList<>(t.tags);
            this.assigneeEmail = t.assigneeEmail;
            this.customerVisible = t.customerVisible;
            this.slaMinutes = t.slaMinutes;
            this.source = t.source;
        }

        public Builder description(String description) {
            Validation.requireMaxLen(description, 5000, "description");
            this.description = description;
            return this;
        }

        public Builder priority(String priority) {
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            this.priority = priority;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = new ArrayList<>(tags);
            return this;
        }

        public Builder addTag(String tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder assignee(String assigneeEmail) {
            if (assigneeEmail != null) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }
            this.assigneeEmail = assigneeEmail;
            return this;
        }

        public Builder customerVisible(boolean customerVisible) {
            this.customerVisible = customerVisible;
            return this;
        }

        public Builder slaMinutes(Integer slaMinutes) {
            Validation.requireRange(slaMinutes, 15, 1440, "slaMinutes");
            this.slaMinutes = slaMinutes;
            return this;
        }

        public Builder source(String source) {
            Validation.requireOneOf(source, "source", "CLI", "WEBHOOK", "EMAIL");
            this.source = source;
            return this;
        }

        public IncidentTicket build() {
            return new IncidentTicket(this);
        }
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
}
