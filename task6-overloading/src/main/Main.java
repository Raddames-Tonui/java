package main;

import geometry.Geometry;
import formatter.StringFormatter;

public class Main {
    public static void main(String[] args) {
        Geometry geo = new Geometry();

        System.out.println("Square area (5): " + geo.area(5));
        System.out.println("Circle area (2.5): " + geo.area(2.5));
        System.out.println("Rectangle area (4.0 x 6.0): " + geo.area(4.0, 6.0));
        System.out.println("Triangle area (3.0 x 5.0): " + geo.area(3.0, 5.0, true));

        StringFormatter formatter = new StringFormatter();

        String input = "hello world from java";
        System.out.println("Capitalized: " + formatter.format(input));
        System.out.println("Repeated 3x: " + formatter.format(input, 3));
        System.out.println("With prefix & suffix: " + formatter.format(input, "[", "]"));
    }
}
