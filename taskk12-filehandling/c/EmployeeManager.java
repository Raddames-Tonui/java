package c;

import java.io.*;
import java.util.*;

public class EmployeeManager {
    static List<Employee> employees = new ArrayList<>();
    static String filePath;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java c.EmployeeManager <file_path>");
            return;
        }

        filePath = args[0];
        File file = new File(filePath);

        // Deserialize if file exists and has content
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                employees = (List<Employee>) ois.readObject();
                System.out.println("Existing employees:");
                employees.forEach(System.out::println);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading employees: " + e.getMessage());
            }
        } else {
            try {
                file.createNewFile();
                System.out.println("File created: " + file.getPath());
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        // Add new employee
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAdd New Employee:");
        System.out.print("Employee Number: ");
        String empNum = scanner.nextLine();
        System.out.print("Employee Name: ");
        String empName = scanner.nextLine();
        System.out.print("Net Salary: ");
        double salary = scanner.nextDouble();
        employees.add(new Employee(empNum, empName, salary));
        saveEmployees();
    }

    static void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(employees);
            System.out.println("Employee list saved");
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}
