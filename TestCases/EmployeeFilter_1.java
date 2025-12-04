
import java.util.ArrayList;
import java.util.List;

public class EmployeeFilter_1 {

    public static class Employee {
        private final String name;
        private final double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }

        public String getName() {
            return name;
        }
    }

    public List<Employee> filterByMinimumSalary(List<Employee> employees, double minSalary) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getSalary() > minSalary) {
                result.add(e);
            }
        }
        return result;
    }
}
