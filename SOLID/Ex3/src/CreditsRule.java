public class CreditsRule implements EligibilityRule {
    private static final int CREDITS_THRESHOLD = 20;

    @Override
    public EligibilityRuleResult evaluate(StudentProfile student) {
        if (student.earnedCredits >= CREDITS_THRESHOLD) {
            return new EligibilityRuleResult(true, null);
        }
        return new EligibilityRuleResult(false, "credits below 20");
    }
}
