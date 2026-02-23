/**
 * LSP base contract:
 * - Precondition: notification must be non-null (enforced here, not by subtypes).
 * - Postcondition: always returns SendResult (never throws for valid input).
 * - Channels that cannot deliver return SendResult.error(...) instead of throwing.
 */
public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    public final SendResult send(Notification n) {
        if (n == null) throw new NullPointerException("Notification must not be null");
        return doSend(n);
    }

    /** Subtypes implement channel-specific delivery here. n is guaranteed non-null. */
    protected abstract SendResult doSend(Notification n);
}

