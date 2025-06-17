package models;

public class Employee {
    private  int employeeId;
    private String employeeNumber;
    private String name;
    private String department;

    // Constructor
    public Employee(int employeeId, String employeeNumber, String name, String department) {
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.department = department;
    }
    public Employee(String empNum, String empName, double salary) {
        //TODO Auto-generated constructor stub
    }
    // Getters
    public int getEmployeeId() {
        return employeeId;
    }
    public String getEmployeeNumber() {
        return employeeNumber;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
    
}
