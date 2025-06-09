
import java.awt.*;

import circle.Circle;



public class Main {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        Circle c2 = new Circle(new Point(3,0), 24);

        System.out.println(c1.getRadius());
        System.out.println(c2.getArea());
        System.out.println(c2.getCenter());


    }
}