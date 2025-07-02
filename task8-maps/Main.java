

import java.util.*;
import java.util.stream.Collectors;
import models.Employee;
import service.EmployeeService;
import java.util.Optional;
import java.util.function.Predicate;
/**
 * Demonstrates the EmployeeService functionalities.
 */
public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();

        // Add employees
        service.addEmployee(new Employee(1, "E1001", "Alice", "HR"));
        service.addEmployee(new Employee(2, "E1002", "Bob", "IT"));
        service.addEmployee(new Employee(3, "E1003", "Charlie", "IT"));
        service.addEmployee(new Employee(4, "E1004", "Diana", "Finance"));

        // Display grouped by department
        System.out.println("=== Employees by Department ===");
        service.displayEmployeesByDepartment();

        // Get employee by ID
        System.out.println("\n=== Get Employee by ID 2 ===");
        service.getEmployeeById(2).ifPresent(System.out::println);

        // Update employee details (change department)
        System.out.println("\n=== Update Employee ID 3 details ===");
        service.updateEmployee(3, "E1003X", "Charlie Brown", "Finance");
        service.displayEmployeesByDepartment();

        // Delete employee by name
        System.out.println("\n=== Delete Employee named 'Alice' ===");
        service.deleteEmployeeByName("Alice");
        service.displayEmployeesByDepartment();

        // Display ordered by name descending
        System.out.println("\n=== Employees ordered by name (desc) ===");
        service.displayEmployeesOrderedBy("name", false);

        // Display filtered by department = Finance
        System.out.println("\n=== Employees filtered by department 'Finance' ===");
        service.displayEmployeesFilteredBy("department", "Finance");

        // Display counts
        System.out.println("\n=== Employee Counts ===");
        service.displayEmployeeCounts();
    }
}
