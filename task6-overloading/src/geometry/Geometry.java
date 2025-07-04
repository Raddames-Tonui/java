package geometry;

public class Geometry {

    public void display(){
        System.out.println("===========GEOMETRY CLASS===============");
    }

    // Overloaded methods to calculate area of different shapes    
    // Area of a square
    public double area(int side){
        return side * side;
    }

     // Area of a circle
    public double area(double radius){
        return Math.PI * radius * radius;
    }

    // Area of a rectangle
    public double area(double length, double width){
        return length * width;
    }

   
    // Area of a triangle
    public double area(double base, double height, boolean isTriangle){
        if (isTriangle){
            return 0.5 * base * height;
        } else {
            return base * height; // This could be interpreted as a rectangle
        }
    }
}
