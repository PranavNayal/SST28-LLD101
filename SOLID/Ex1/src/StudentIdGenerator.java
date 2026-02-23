public class StudentIdGenerator {
    public String generateId(int currentCount) {
        return IdUtil.nextStudentId(currentCount);
    }
}
