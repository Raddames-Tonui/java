import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // loops(); 
        // nestedLoops();  
        // breakAndContinue();
        isPrime();
    }
    public static void loops(){
        Scanner input = new Scanner(System.in);

        int number;
        do{
            System.out.println("Enter a number between 1 and 10: ");
            number = input.nextInt();
        }while (number < 1 || number > 10);

        System.out.println("You entered a valid number: " + number);

        int i = 5;
        while (i <= 10) {
            System.out.println(i++);
        }
        
        for (int j = 0; j < 5; j++) {
            System.out.println("For loop iteration: " + j);
        }
        
        do {
            System.out.println("Do-while loop iteration: " + i);
            i--;
        } while (i > 0);
    }

    public static void nestedLoops(){
        for (int i = 1; i <= 10; i++){
            for (int j = 1; j <= 10; j++){
                System.out.println((i * j) + " " );
            }
            System.out.println();
        }
    }

    public static void breakAndContinue(){
        for (int i = 1; i <= 10; i++){
            if (i == 5) {
                System.out.println("Breaking at " + i);
                break; // exits the loop when i is 5
            }
            if (i % 2 == 0) {
                System.out.println("Skipping even number: " + i);
                continue; // skips the rest of the loop iteration for even numbers
            }
            System.out.println("Current number: " + i);
        }
    }

    public static boolean isPrime() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n < 2) {
            return false; // 0 and 1 are not prime
        }
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false; // n is not prime
            }
        }
        return true; // n is prime
    }
}
