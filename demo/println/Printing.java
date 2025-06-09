
public class Printing {
    
    public static void main(String[] args) {
        System.out.println("This is the Printing Class");
        hurray();
    }
    public static void hurray(){
        System.out.println("Hurray! You have understood packages!");
        privateMethod();
    }
    private static void privateMethod() {
        System.out.println("This is a private method in  class");
    }
}
