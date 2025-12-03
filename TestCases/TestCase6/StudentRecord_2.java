public class StudentRecord_2 {

    private String fullName;  // renamed
    private int studentId;
    private double gpa;

    public StudentRecord_2(String fullName, int studentId, double gpa) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public void printInfo() {
        System.out.println("Name: " + fullName);
        System.out.println("ID: " + studentId);
        System.out.println("GPA: " + gpa);
    }

    public double getGpa() {
        return gpa;
    }

    public void adjustGpa(double delta) { // modified update method
        this.gpa += delta;
    }
}
