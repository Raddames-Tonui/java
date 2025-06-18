public class FullTimeEmployee  extends Employees{
    private double basicSalary;
    private double benefits;

// constructor
    public FullTimeEmployee(int employeeNumber, String employeeName, double basicSalary, double benefits) {
        super(employeeNumber, employeeName);
        this.basicSalary = basicSalary;
        this.benefits = benefits;
    }

//  Oerride the calculator salary from parent Employees
    @Override
    public void calculateSalary(){
        netSalary = basicSalary + benefits;
        System.out.println("Full-Time Employee Salary: " + netSalary);
    }
}
