
class Calc {
    // This method is not final, so it can be overridden in subclasses
    public int add(int a, int b){
        return a + b;
    }

    public void display(){
        System.out.println("This is a method in Calc class");
    }
    
}

class AdvCalc extends Calc{
    // This method overrides the add method from Calc class
    // Note: Overriding has the same method parameters with parent class unlike overloading eg: 
    // Oveloading: add(int a, int b) and add(int a, int b, int c)  
    // Overriding: add(int a, int b) { return a + b} and add(int a, int b) { return a + b + 1; }
    
    @Override
    public int add(int a, int b){
        return a + b +1;
    }
    
}

public class Demo {
    public static void main(String[] args) {
        Calc calc = new Calc();
        calc.display(); // Output: This is a method in Calc class
        System.out.println("Sum using Calc: " + calc.add(5, 10)); // Output: 15

        AdvCalc advCalc = new AdvCalc();
        advCalc.display(); // Output: This is a method in Calc class
        System.out.println("Sum using AdvCalc: " + advCalc.add(5, 10)); // Output: 16
    }
    
}
