package Chapter11.Ex03;

public class Student {
    private String studentName;

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            if (this.studentName.equals(((Student) obj).getStudentName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return studentName.hashCode();
    }
}
