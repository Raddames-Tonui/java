package org.example.demo;

public class Person {
    private final String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void secretMethod(){
        System.out.println("This is a secret method!");
    }

    public static void myStaticMethod(){
        System.out.println("Hello from a static method!");
    }
}
