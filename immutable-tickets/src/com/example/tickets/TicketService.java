package com.example.tickets;

/**
 * Service layer that creates and manages immutable tickets.
 *
 * All operations return new IncidentTicket instances.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return IncidentTicket.builder(id, reporterEmail, title)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assignee(assigneeEmail)
                .build();
    }
}
