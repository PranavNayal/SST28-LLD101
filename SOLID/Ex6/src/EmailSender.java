public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // LSP fix: preserve full message content without truncation
        String subject = n.subject == null ? "" : n.subject;
        String body = n.body == null ? "" : n.body;
        String email = n.email == null ? "" : n.email;
        
        System.out.println("EMAIL -> to=" + email + " subject=" + subject + " body=" + body);
        audit.add("email sent");
    }
}
