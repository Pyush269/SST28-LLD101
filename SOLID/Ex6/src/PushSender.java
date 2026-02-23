// Stretch goal: new sender added without changing any existing sender.
// Honors the LSP contract: never throws, returns SendResult consistently.
public class PushSender extends NotificationSender {
    public PushSender(AuditLog audit) { super(audit); }

    @Override
    protected SendResult doSend(Notification n) {
        System.out.println("PUSH -> to=" + n.email + " body=" + n.body);
        audit.add("push sent");
        return SendResult.ok();
    }
}
