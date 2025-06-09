public class  PartTimeEmployee extends Employees{
    private int hoursWorked;
    private double ratePerHour;

    public PartTimeEmployee(int employeeNumber, String employeeName, int hoursWorked, double ratePerHour){
        super(employeeNumber, employeeName);
        this.hoursWorked = hoursWorked;
        this.ratePerHour = ratePerHour;
    }

    @Override
    public void calculateSalary(){
        netSalary = hoursWorked * ratePerHour;
        System.out.println("Part-Time Employee Salary: " + netSalary);
    }
 
    
}
