import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        manager();
    }

    public static void manager() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            String name = null;
            String number = null;
            double salary = 0;

            try {
                //  NAME INPUT & VALIDATION
                System.out.println("Enter employee name: ");
                name = sc.nextLine();

                if (name.isBlank()) {
                    throw new EmployeeDataException("Name cannot be blank.");
                }
                if (name.length() < 3) {
                    throw new EmployeeDataException("Name must be at least 3 characters.");
                }

                //  NUMBER INPUT & VALIDATION
                System.out.println("Enter employee number: ");
                number = sc.nextLine();

                if (number.isBlank()) {
                    throw new EmployeeDataException("Number cannot be blank.");
                }
                if (number.length() > 5) {
                    throw new EmployeeDataException("Number must be 5 characters or less.");
                }

                //  SALARY INPUT & VALIDATION
                System.out.println("Enter employee salary: ");
                String salaryInput = sc.nextLine();

                try {
                    salary = Double.parseDouble(salaryInput);
                } catch (NumberFormatException e) {
                    throw new EmployeeDataException("Salary must be a valid number.");
                }

                if (salary <= 0) {
                    throw new EmployeeDataException("Salary must be greater than zero.");
                }

                //  Create employee
                Employee employee = new Employee(number, name, salary);

                System.out.println("---------- Created Employee ------------------");
                System.out.println("Name: " + employee.getEmployeeName().toUpperCase());
                System.out.println("Number: " + employee.getEmployeeNumber());
                System.out.println("Salary: " + employee.getNetSalary());

                running = false; // Exit the loop

            } catch (EmployeeDataException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please re-enter the data.\n");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            } 
        }

        sc.close();
    }
}
