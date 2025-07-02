package task5_Interfaces.functional_interface;

@FunctionalInterface
interface Addition {
    int add(int a, int b);
}

public class LamdaDemo {
    public static void main(String[] args) {
        Addition obj = (a,b) -> a+b;

//        obj.add(10,20);
        System.out.println(obj.add(10,20));
    }
}
