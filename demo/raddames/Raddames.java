package raddames;

public class Raddames {
    
    public static void main(String[] args) {
        System.out.println("This is the Raddames Class");
        hurray();
    }
    public static void hurray(){
        System.out.println("Hurray Raddames! You have understood packages!");
        privateMethod();
    }
    private static void privateMethod() {
        System.out.println("This is a private method in Raddames class");
    }
}
