package c;

import java.io.Serializable;

public class Employee implements Serializable {
    private String employeeNumber;
    private String employeeName;
    private double netSalary;

    public Employee(String employeeNumber, String employeeName, double netSalary) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.netSalary = netSalary;
    }

    @Override
    public String toString() {
        return employeeNumber + " - " + employeeName + " - KES " + netSalary;
    }
}
