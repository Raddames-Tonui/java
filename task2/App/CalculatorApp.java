package App;

import MathLibrary.MathOperations;
import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose operation: 1-Add 2-Sub 3-Mul 4-Div");
        int choice = sc.nextInt();

        System.out.print("Enter first number: ");
        int a = sc.nextInt();

        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        int result = switch (choice) {
            case 1 -> MathOperations.add(a, b);
            case 2 -> MathOperations.subtract(a, b);
            case 3 -> MathOperations.multiply(a, b);
            case 4 -> MathOperations.divide(a, b);
            default -> {
                System.out.println("Invalid choice."); yield 0;
            }
        };

        System.out.println("Result: " + result);
    }
}
