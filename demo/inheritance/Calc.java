public class Calc {
    double a = 0;
    double b = 0;

    public Calc() {
        this.a = 0;
        this.b = 0;
    }

    public Calc(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

  public static void main(String[] args) {
    SuperAdvCalc s1 = new SuperAdvCalc();
    double res1 = s1.add(5,7);
    int ress1 = (int) res1; // Casting double to int
    double res2 = s1.subtract(5,3);
    double res3 = s1.multiply(4,5);
    double res4 = s1.divide(10,2);
    double res5 = s1.power(10,2); // This will throw an exception
    

    System.out.println("Add: " + ress1 + "\nSub: " + res2 
                       + "\nMul: " + res3 + "\nDiv: " + res4 +"\nPow " + res5); 
  }
}
