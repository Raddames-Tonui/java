public class Main {
    public static void main(String[] args) {
        FullTimeEmployee fullTime = new FullTimeEmployee(1, "Alice", 50000, 10000);
        PartTimeEmployee partTime = new PartTimeEmployee(2, "Bob", 40, 500);

        System.out.println("== Full-Time Employee ==");
        fullTime.displayEmployeeDetails();
        fullTime.calculateSalary();

        System.out.println("\n== Part-Time Employee ==");
        partTime.displayEmployeeDetails();
        partTime.calculateSalary();
    }
}
