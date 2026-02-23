import java.util.*;

public interface EligibilityRule {
    EligibilityRuleResult evaluate(StudentProfile student);
}

class EligibilityRuleResult {
    public final boolean passed;
    public final String failureReason;

    public EligibilityRuleResult(boolean passed, String failureReason) {
        this.passed = passed;
        this.failureReason = failureReason;
    }
}
