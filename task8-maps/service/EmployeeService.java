package service;

import models.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class to handle operations on the Map of departments to employees.
 */
public class EmployeeService {

    // Map: Department -> List of Employees
    private final Map<String, List<Employee>> departmentEmployeeMap;

    public EmployeeService() {
        this.departmentEmployeeMap = new HashMap<>();
    }

    /**
     * Add a new employee to the appropriate department.
     * If department does not exist, create new list.
     */
    public void addEmployee(Employee employee) {
        departmentEmployeeMap
            .computeIfAbsent(employee.getDepartment(), k -> new ArrayList<>())
            .add(employee);
    }

    /**
     * Search employee by ID, number, or name across all departments.
     */
    public Optional<Employee> getEmployeeById(int employeeId) {
        return departmentEmployeeMap.values().stream()
                .flatMap(List::stream)
                .filter(e -> e.getEmployeeId() == employeeId)
                .findFirst();
    }

    public Optional<Employee> getEmployeeByNumber(String employeeNumber) {
        return departmentEmployeeMap.values().stream()
                .flatMap(List::stream)
                .filter(e -> e.getEmployeeNumber().equalsIgnoreCase(employeeNumber))
                .findFirst();
    }

    public Optional<Employee> getEmployeeByName(String name) {
        return departmentEmployeeMap.values().stream()
                .flatMap(List::stream)
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Update employee details, including department change.
     * If department changes, move employee accordingly.
     */
    public boolean updateEmployee(int employeeId, String newNumber, String newName, String newDepartment) {
        Optional<Employee> optionalEmployee = getEmployeeById(employeeId);
        if (optionalEmployee.isEmpty()) {
            return false;
        }

        Employee employee = optionalEmployee.get();
        String oldDepartment = employee.getDepartment();

        // Update fields
        employee.setEmployeeNumber(newNumber);
        employee.setName(newName);

        // If department changed, move employee to new department list
        if (!oldDepartment.equalsIgnoreCase(newDepartment)) {
            // Remove from old department list
            List<Employee> oldDeptList = departmentEmployeeMap.get(oldDepartment);
            oldDeptList.remove(employee);
            if (oldDeptList.isEmpty()) {
                departmentEmployeeMap.remove(oldDepartment);
            }

            // Update department and add to new department list
            employee.setDepartment(newDepartment);
            departmentEmployeeMap
                .computeIfAbsent(newDepartment, k -> new ArrayList<>())
                .add(employee);
        }

        return true;
    }

    /**
     * Delete employee by any property (id, number, name).
     * Removes employee and cleans up empty department entries.
     */
    public boolean deleteEmployeeById(int employeeId) {
        for (Map.Entry<String, List<Employee>> entry : departmentEmployeeMap.entrySet()) {
            List<Employee> employees = entry.getValue();
            boolean removed = employees.removeIf(e -> e.getEmployeeId() == employeeId);
            if (removed) {
                if (employees.isEmpty()) {
                    departmentEmployeeMap.remove(entry.getKey());
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteEmployeeByNumber(String employeeNumber) {
        for (Map.Entry<String, List<Employee>> entry : departmentEmployeeMap.entrySet()) {
            List<Employee> employees = entry.getValue();
            boolean removed = employees.removeIf(e -> e.getEmployeeNumber().equalsIgnoreCase(employeeNumber));
            if (removed) {
                if (employees.isEmpty()) {
                    departmentEmployeeMap.remove(entry.getKey());
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteEmployeeByName(String name) {
        for (Map.Entry<String, List<Employee>> entry : departmentEmployeeMap.entrySet()) {
            List<Employee> employees = entry.getValue();
            boolean removed = employees.removeIf(e -> e.getName().equalsIgnoreCase(name));
            if (removed) {
                if (employees.isEmpty()) {
                    departmentEmployeeMap.remove(entry.getKey());
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Display all employees grouped by department.
     */
    public void displayEmployeesByDepartment() {
        departmentEmployeeMap.forEach((department, employees) -> {
            System.out.println("Department: " + department);
            employees.forEach(e -> System.out.println("\t" + e));
        });
    }

    /**
     * Display employees ordered by a property (id, number, name).
     * @param property Property to sort by ("id", "number", "name")
     * @param ascending true for ascending order, false for descending
     */
    public void displayEmployeesOrderedBy(String property, boolean ascending) {
        Comparator<Employee> comparator;

        switch (property.toLowerCase()) {
            case "id":
                comparator = Comparator.comparingInt(Employee::getEmployeeId);
                break;
            case "number":
                comparator = Comparator.comparing(Employee::getEmployeeNumber, String.CASE_INSENSITIVE_ORDER);
                break;
            case "name":
                comparator = Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                System.out.println("Invalid property for sorting.");
                return;
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        List<Employee> allEmployees = departmentEmployeeMap.values().stream()
                .flatMap(List::stream)
                .sorted(comparator)
                .collect(Collectors.toList());

        allEmployees.forEach(System.out::println);
    }

    /**
     * Display employees filtered by a property using Streams.
     * @param property Property to filter by ("department", "name", "number")
     * @param value Value to match (case-insensitive)
     */
    public void displayEmployeesFilteredBy(String property, String value) {
        List<Employee> filteredEmployees = departmentEmployeeMap.values().stream()
                .flatMap(List::stream)
                .filter(e -> {
                    switch (property.toLowerCase()) {
                        case "department":
                            return e.getDepartment().equalsIgnoreCase(value);
                        case "name":
                            return e.getName().equalsIgnoreCase(value);
                        case "number":
                            return e.getEmployeeNumber().equalsIgnoreCase(value);
                        default:
                            return false;
                    }
                })
                .collect(Collectors.toList());

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found for filter: " + property + " = " + value);
        } else {
            filteredEmployees.forEach(System.out::println);
        }
    }

    /**
     * Display total number of employees per department and overall total.
     */
    public void displayEmployeeCounts() {
        int totalCount = 0;
        for (Map.Entry<String, List<Employee>> entry : departmentEmployeeMap.entrySet()) {
            int count = entry.getValue().size();
            System.out.println("Department " + entry.getKey() + " has " + count + " employee(s).");
            totalCount += count;
        }
        System.out.println("Overall total employees: " + totalCount);
    }
}
