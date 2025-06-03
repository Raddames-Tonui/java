class FullTimeEmployee extends Employees {
    private double basicSalary;
    private double benefits;

    public FullTimeEmployee(int employeeNumber, String employeeName, double basicSalary, double benefits) {
        super(employeeNumber, employeeName);
        this.basicSalary = basicSalary;
        this.benefits = benefits;
    }

    @Override
    public void calculateSalary() {
        netSalary = basicSalary + benefits;
        System.out.println("Full-Time Employee Salary: " + netSalary);
    }
}
