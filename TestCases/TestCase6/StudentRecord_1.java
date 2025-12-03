public class StudentRecord_1 {

    private String name;
    private int id;
    private double gpa;

    public StudentRecord_1(String name, int id, double gpa) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }

    public void printInfo() {
        System.out.println("Student: " + name + " (ID: " + id + ")");
        System.out.println("GPA: " + gpa);
    }

    public void updateGpa(double newGpa) {
        this.gpa = newGpa;
    }

    public double getGpa() {
        return gpa;
    }
}
