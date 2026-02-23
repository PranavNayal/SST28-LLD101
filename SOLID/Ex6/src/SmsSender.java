public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // LSP: SMS channel doesn't use subject field, only body and phone
        String phone = n.phone == null ? "" : n.phone;
        String body = n.body == null ? "" : n.body;
        
        System.out.println("SMS -> to=" + phone + " body=" + body);
        audit.add("sms sent");
    }
}
