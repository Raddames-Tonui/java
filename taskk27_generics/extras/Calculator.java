package extras;


// BOUNDED GENERICS
public class Calculator<T extends Number> {
    private T num;

    public Calculator(T num) {
        this.num = num;
    }

    public double square() {
        return num.doubleValue() * num.doubleValue();
    }

    public double circleArea() {
        return Math.PI * Math.pow(num.doubleValue(), 2);
    }

    public void displaySquare() {
        System.out.println("🔷 Calculating area of square...");
        System.out.println("Type: " + getType());
        System.out.println("Side: " + num);
        System.out.println("Area: " + square());
    }

    public void displayCircle() {
        System.out.println("⚪ Calculating area of circle...");
        System.out.println("Type: " + getType());
        System.out.println("Radius: " + num);
        System.out.println("Area: " + circleArea());
    }

    public String getType() {
        if (num instanceof Integer) return "Integer";
        else if (num instanceof Double) return "Double";
        else if (num instanceof Float) return "Float";
        else if (num instanceof Long) return "Long";
        else return "Unknown Number type";
    }
}



/*
// <T extends Number>
Number is an abstract class with the following methods
public abstract class Number implements java.io.Serializable {
    public abstract int intValue();
    public abstract long longValue();
    public abstract float floatValue();
    public abstract double doubleValue();
    public abstract byte byteValue();
    public abstract short shortValue();
}

 */