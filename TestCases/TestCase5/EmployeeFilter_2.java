import java.util.ArrayList;
import java.util.List;

public class EmployeeFilter_2 {

    public static class Employee {
        private final String fullName;   // renamed from 'name'
        private final double salary;

        public Employee(String fullName, double salary) {
            this.fullName = fullName;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }

        public String getFullName() {
            return fullName;
        }
    }

    // slight behavioral change: inclusive >= comparison
    public List<Employee> filterByMinimumSalary(List<Employee> employees, double minSalary) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getSalary() >= minSalary) {
                result.add(e);
            }
        }
        return result;
    }
}
