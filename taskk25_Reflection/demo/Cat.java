package taskk25_Reflection.demo;

public class Cat {
    private final String name;
    private  int age;

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
    }

    public  String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void meowMethod (){
        System.out.println("Meow in the public method");
    }
    private void thisIsPrivateMethod(){
        System.out.println("This is private method");
    }

    public static void thisIsPublicStaticMethod(){
        System.out.println("This is public static method");
    }
    private static void thisIsPrivateStaticMethod(){
        System.out.println("This is private static method");
    }
}
