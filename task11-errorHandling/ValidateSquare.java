
import java.util.Scanner;
import  RadException;

public class ValidateSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;

        while(!valid){
            try {
                System.out.println("Please enter an Integer: ");
                int number = Integer.parseInt(sc.nextLine());
                System.out.println("Squared: "+ (number*number));
                valid = true;
            } catch (RadException e){
                System.out.println("Please enter an Integer");
            }
        }
    }
}
