// Abstact class Employees
public abstract class Employees {
    // Instance variables
    protected int employeeNumber;
    protected String employeeName;
    protected double netSalary;

    // Constructor to initialize employee details
    public Employees( int employeeNumber, String employeeName){
        this.employeeName = employeeName;
        this.employeeNumber = employeeNumber;
    }

    // Abstact method to calculate salary
    public abstract void calculateSalary();

    // Method to display employee details (Non abstract)
    public void displayEmployeeDetails(){
        System.out.println("Employee Number: "+ employeeNumber);
        System.out.println("Employee Name: "+ employeeName);
    }

    
}
