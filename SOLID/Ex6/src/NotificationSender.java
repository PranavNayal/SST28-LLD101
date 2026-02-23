/**
 * Abstract base class for sending notifications via different channels.
 * 
 * CONTRACT:
 * - send(Notification) must not throw exceptions for valid input
 * - All fields of Notification that are applicable to the channel should be used
 * - No silent truncation or data loss is allowed
 * - Recipient information (email or phone) should be accepted in standard formats without strict validation
 * 
 * FIELD USAGE:
 * - EmailSender: uses subject, body, and email
 * - SmsSender: uses body and phone (subject is not used for SMS)
 * - WhatsAppSender: uses body and phone (international format with +)
 * 
 * IMPLEMENTATION RULES:
 * - Subclasses must be substitutable without caller needing to know the specific type
 * - Subclasses must handle null fields gracefully
 * - Subclasses must preserve all content without truncation
 */
public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    public abstract void send(Notification n);
}
