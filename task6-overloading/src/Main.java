import geometry.Geometry;
import java.util.Scanner;
import formatter.StringFormatter;

public class Main {
    public static void main(String[] args) {
        Geometry geo = new Geometry();

        // Geometry Overloading
        geo.display();
        System.out.println("Square area (5): " + geo.area(5));
        System.out.println("Circle area (2.5): " + geo.area(2.5));
        System.out.println("Rectangle area (4.0 x 6.0): " + geo.area(4.0, 6.0));
        System.out.println("Triangle area (3.0 x 5.0): " + geo.area(3.0, 5.0, true));

        // String Formatter Overloading
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a String to format: ");
        String userInput = scanner.nextLine();

        StringFormatter formatter = new StringFormatter();

        System.out.println("Capitalized: " + formatter.format(userInput));
        System.out.println("Repeated 3x: " + formatter.format(userInput, 3));
        System.out.println("With prefix & suffix: " + formatter.format(userInput, "[", "]"));

        scanner.close();
    }
}
