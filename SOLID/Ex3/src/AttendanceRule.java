public class AttendanceRule implements EligibilityRule {
    private static final int ATTENDANCE_THRESHOLD = 75;

    @Override
    public EligibilityRuleResult evaluate(StudentProfile student) {
        if (student.attendancePct >= ATTENDANCE_THRESHOLD) {
            return new EligibilityRuleResult(true, null);
        }
        return new EligibilityRuleResult(false, "attendance below 75");
    }
}
