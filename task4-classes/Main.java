public class Main {
    public static void main(String[] args) {
        Employees fullTime = new FullTimeEmployee(1, "James", 2000, 10000);
        Employees partTime = new PartTimeEmployee(2, "Sarah", 20, 15.0);
    
        System.out.println("== Full-Time Employee ==");
        fullTime.displayEmployeeDetails();
        fullTime.calculateSalary();

        System.out.println("\n== Part-Time Employee ==");
        partTime.displayEmployeeDetails();
        partTime.calculateSalary();
    }
    
}
