abstract class Employees {
    protected int employeeNumber;
    protected String employeeName;
    protected double netSalary;

    public Employees(int employeeNumber, String employeeName) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
    }

    public abstract void calculateSalary();

    public void displayEmployeeDetails() {
        System.out.println("Employee Number: " + employeeNumber);
        System.out.println("Employee Name: " + employeeName);
    }
}
