import java.util.Scanner;

public class ValidateSquare {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Boolean valid = false;

        while(!valid) {
            try{
                System.out.println("Enter a number: ");
                int a = sc.nextInt();

                if (a < 0){
                    throw new CustomError(" Negative numbers are not allowed.");
                }
                System.out.println("The square of the number is: " + (a * a));
                valid = true; // if valid input exit the code
            
            }catch(CustomError e){
                System.out.println( "This is a custom exception" + e.getMessage());
            }
            
            catch(Exception e){
                System.out.println("Number is not valid: " + e.getMessage());
                sc.nextLine(); // Clear invalid token fom the scanner
            };
        }
        System.out.println("Closing the scanner");
        sc.close();


    }
}