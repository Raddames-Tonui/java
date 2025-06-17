import java.util.Scanner;

public class UserInputValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Validate name: Letters and spaces only
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        if (name.matches("^[A-Za-z ]+$")) {
            System.out.println("Valid name");
        } else {
            System.out.println("Invalid name");
        }

        // Validate email format
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        if (email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")) {
            System.out.println("Valid email");
        } else {
            System.out.println("Invalid email.");
        }

        // Validate Kenyan mobile number
        System.out.print("Enter your mobile number: ");
        String mobile = sc.nextLine();
        if (mobile.matches("^(254|\\+254|07|01)[0-9]{8}$")) {
            System.out.println("Valid mobile number");
        } else {
            System.out.println("Invalid mobile number");
        }

        sc.close();
    }
}
