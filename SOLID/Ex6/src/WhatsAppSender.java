public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // LSP fix: normalize phone format instead of throwing exception
        String phone = n.phone == null ? "" : n.phone;
        String body = n.body == null ? "" : n.body;
        
        // Normalize phone if it doesn't start with +
        if (!phone.isEmpty() && !phone.startsWith("+")) {
            phone = "+" + phone;
        }
        
        System.out.println("WA -> to=" + phone + " body=" + body);
        audit.add("wa sent");
    }
}
