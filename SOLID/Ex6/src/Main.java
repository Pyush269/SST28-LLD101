public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms = new SmsSender(audit);
        NotificationSender wa = new WhatsAppSender(audit);

        // LSP: no try-catch needed — all senders return SendResult consistently.
        email.send(n);
        sms.send(n);

        SendResult waResult = wa.send(n);
        if (waResult.isError) {
            System.out.println("WA ERROR: " + waResult.errorMessage);
        }

        System.out.println("AUDIT entries=" + audit.size());

        // Stretch goal: new sender without changing existing ones.
        System.out.println();
        NotificationSender push = new PushSender(audit);
        push.send(n);
        System.out.println("AUDIT entries=" + audit.size());
    }
}
