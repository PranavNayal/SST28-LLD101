public class DisciplinaryRule implements EligibilityRule {
    @Override
    public EligibilityRuleResult evaluate(StudentProfile student) {
        if (student.disciplinaryFlag == LegacyFlags.NONE) {
            return new EligibilityRuleResult(true, null);
        }
        return new EligibilityRuleResult(false, "disciplinary flag present");
    }
}
