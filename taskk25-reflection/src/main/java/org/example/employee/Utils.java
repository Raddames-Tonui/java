package org.example.employee;

public class Utils {
    public static void displayEmployeeDetails(Employee employee){
        System.out.printf("Employee Id: %d%nEmployee Name: %s%nEmployee Number: %s%nDepartment: %s%n ",
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmployeeNumber(),
                employee.getDepartment()
        );
    }

    /**
     * | Symbol | Meaning                                      |
     * | ------ | -------------------------------------------- |
     * | `%d`   | Formats an integer (e.g., employee ID)       |
     * | `%s`   | Formats a string (e.g., name, number, dept.) |
     * | `%n`   | Platform-independent newline (line break)    |
     * */
}
