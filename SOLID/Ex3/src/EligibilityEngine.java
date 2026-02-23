import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;
        this.rules = new ArrayList<>();
        this.rules.add(new DisciplinaryRule());
        this.rules.add(new CgrRule());
        this.rules.add(new AttendanceRule());
        this.rules.add(new CreditsRule());
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        // Check rules in order; stop at first failure (else-if pattern)
        for (EligibilityRule rule : rules) {
            EligibilityRuleResult result = rule.evaluate(s);
            if (!result.passed) {
                status = "NOT_ELIGIBLE";
                reasons.add(result.failureReason);
                break;  // Stop at first failure to match original behavior
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
