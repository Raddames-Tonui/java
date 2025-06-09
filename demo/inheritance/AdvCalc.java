
public class AdvCalc extends Calc {
    
    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b){
        if (b == 0){
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / (double)b;
    }
}
