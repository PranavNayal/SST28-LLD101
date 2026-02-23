public class CgrRule implements EligibilityRule {
    private static final double CGR_THRESHOLD = 8.0;

    @Override
    public EligibilityRuleResult evaluate(StudentProfile student) {
        if (student.cgr >= CGR_THRESHOLD) {
            return new EligibilityRuleResult(true, null);
        }
        return new EligibilityRuleResult(false, "CGR below 8.0");
    }
}
