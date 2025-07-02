package task5_Interfaces.functional_interface;

@FunctionalInterface
interface A {
    void show(int a);
}

//Create a class that implements A
class B implements A {

//    The show() method is now overridden since it had not been instantiated.
    @Override
    public void show(int a) {
        System.out.println("This is an implementing class of A");
    }
}
public class Demo {
    public static void main(String[] args) {
        A obj = a -> {  System.out.println("This is an implementing class of A " + a);   };

        obj.show(5);
    }
}