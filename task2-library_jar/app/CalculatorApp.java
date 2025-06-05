package app;

import java.util.Scanner;
import mathlib.MathLibrary;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        // Validate operation choice
        while (choice < 1 || choice > 4) {
            System.out.println("Choose operation:\n 1-Add\n 2-Subtract\n 3-Multiply\n 4-Divide");
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please choose a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                input.next(); // Clear invalid input
            }
        }

        // Prompt and validate first number
        Double a = null;
        while (a == null) {
            System.out.print("Enter first number: ");
            if (input.hasNextDouble()) {
                a = input.nextDouble();
            } else {
                System.out.println("Invalid number. Try again.");
                input.next(); // Clear invalid input
            }
        }
        
        Double b = null;
        while (b == null){
            System.out.println("Ener second number: ");
            if(input.hasNextDouble()){
                b = input.nextDouble();
            } else {
                System.out.println("Invalid number: Try again.");
                input.next(); // Clear invalid input
            }
            }
     
        input.close();

        // Perform operation
        double result;
        try {
            result = switch (choice) {
                case 1 -> MathLibrary.add(a, b);
                case 2 -> MathLibrary.subtract(a, b);
                case 3 -> MathLibrary.multiply(a, b);
                case 4 -> MathLibrary.divide(a, b);
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            };
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error during calculation: " + e.getMessage());
        }
    }
}
