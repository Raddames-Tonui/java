
public class Employee {
    private String employeeNumber; 
    private String employeeName; 
    private double netSalary;


    public Employee(String employeeNumber, String employeeName, double netSalary) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.netSalary = netSalary;
    }


    public String getEmployeeNumber() {
        return employeeNumber;
    }


    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }


    public String getEmployeeName() {
        return employeeName;
    }


    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }


    public double getNetSalary() {
        return netSalary;
    }


    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }    

}
