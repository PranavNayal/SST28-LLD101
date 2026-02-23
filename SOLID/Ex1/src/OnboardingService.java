import java.util.*;

public class OnboardingService {
    private final FakeDb db;
    private final InputParser parser;
    private final RegistrationValidator validator;
    private final StudentIdGenerator idGenerator;
    private final ConfirmationPrinter printer;

    public OnboardingService(FakeDb db) {
        this.db = db;
        this.parser = new InputParser();
        this.validator = new RegistrationValidator();
        this.idGenerator = new StudentIdGenerator();
        this.printer = new ConfirmationPrinter();
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        // Parse input
        Map<String, String> kv = parser.parse(raw);
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        // Validate
        List<String> errors = validator.validate(name, email, phone, program);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        // Generate ID and create record
        String id = idGenerator.generateId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        // Save and print success
        db.save(rec);
        printer.printSuccess(id, db.count(), rec);
    }
}
