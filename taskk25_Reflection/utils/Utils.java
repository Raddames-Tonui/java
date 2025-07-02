package taskk25_Reflection.utils;

import taskk25_Reflection.employee.Employee;

public class Utils {

    public static void displayEmployeeDetails(Employee employee) {
        System.out.printf(
                "Employee Id: %d%nEmployee Name: %s%nEmployee Number: %s%nDepartment: %s%n",
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmployeeNumber(),
                employee.getDepartment()
        );
    }
}
