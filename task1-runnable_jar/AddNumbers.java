import java.util.Scanner;

public class AddNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter first number: ");
        int firstNumber = input.nextInt();

        System.out.println("Enter second number: ");
        int secondNumber =input.nextInt();

        int sum = firstNumber + secondNumber;
        System.out.println("The sum is " + sum);
        input.close();

    }
}
