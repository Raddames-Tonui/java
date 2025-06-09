package circle;

import java.awt.*;

public class Circle {
    double radius;
    Point center;

    // Default constructor
    public Circle() {
        this.center = new Point(0, 0);
        this.radius = 1.0;
    }

    // Parameterized constructor
    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    // Setter methods
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    // Getter methods
    public double getRadius() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }

    // Circle calculations
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}
