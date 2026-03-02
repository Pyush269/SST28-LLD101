import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demonstrates the immutability guarantees of the refactored IncidentTicket.
 *
 * After refactor:
 * - direct mutation does not compile (no setters)
 * - "updates" via service return a NEW ticket; original is unchanged
 * - tags list is unmodifiable from outside
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Create a fully-formed ticket via Builder (all validation in build())
        IncidentTicket original = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created:  " + original);

        // 2. "Assign" returns a NEW ticket — original is unchanged
        IncidentTicket assigned = service.assign(original, "agent@example.com");
        System.out.println("\nAssigned: " + assigned);
        System.out.println("Original still has no assignee: " + original.getAssigneeEmail());

        // 3. "Escalate" returns another NEW ticket — previous is unchanged
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nEscalated: " + escalated);
        System.out.println("Assigned ticket priority unchanged: " + assigned.getPriority());

        // 4. Tags list is unmodifiable — external mutation throws UnsupportedOperationException
        List<String> tags = original.getTags();
        System.out.println("\nAttempting external tag mutation on original...");
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("ERROR: mutation succeeded (should not happen)");
        } catch (UnsupportedOperationException e) {
            System.out.println("Blocked: tags list is immutable (UnsupportedOperationException)");
        }
        System.out.println("Original tags unchanged: " + original.getTags());

        // 5. Manual Builder usage (fluent API)
        IncidentTicket manual = new IncidentTicket.Builder("INC-0042", "dev@uni.edu", "DB connection pool exhausted")
                .priority("HIGH")
                .source("WEBHOOK")
                .slaMinutes(120)
                .addTag("DATABASE")
                .addTag("INFRA")
                .customerVisible(true)
                .build();
        System.out.println("\nManually built: " + manual);
    }
}

