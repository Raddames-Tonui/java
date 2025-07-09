package org.example.demo;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // REFLECTION WITH FIELDS
        Person p1 = new Person("James", 16);

        System.out.println("The class of p1 is: " + p1.getClass().getName() + "\n");

        // ========== FIELD REFLECTION ==========
        Field[] fields = p1.getClass().getDeclaredFields();
        System.out.println("------- Field Reflection ---------");
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(p1);
            System.out.println("field: " + field.getName() + " = " + value);

            if (field.getName().equals("name")) {
                field.set(p1, "Sharline");
            }
        }
        System.out.println("Changed private name to: " + p1.getName() + "\n");

        // ========== METHOD REFLECTION ==========
        Method[] methods = p1.getClass().getDeclaredMethods();
        System.out.println("------- Method Reflection --------");
        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println("Method: " + method.getName() +
                    ", Return: " + method.getReturnType().getSimpleName() +
                    ", Params: " + method.getParameterCount());
        }

        System.out.println("\nInvoking specific method:");
        for (Method method : methods) {
            if (method.getName().equals("myStaticMethod")) {
                method.invoke(null); // static method: no instance needed
            }
        }

        // ========== CONSTRUCTOR REFLECTION ==========
        Constructor<?>[] constructors = p1.getClass().getDeclaredConstructors();
        System.out.println("\n------- Constructor Reflection --------");
        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor: " + constructor +
                    ", Params: " + constructor.getParameterCount());
        }

        // Instantiate a new object via reflection
        Constructor<?> constructor = p1.getClass().getConstructor(String.class, int.class);
        Object p2 = constructor.newInstance("Michael", 22);
        System.out.println("Created new instance via constructor: " + ((Person) p2).getName());

        // ========== CLASS METADATA ==========
        System.out.println("\n------- Class Metadata --------");
        Class<?> clazz = p1.getClass();
        System.out.println("Is interface? " + clazz.isInterface());
        System.out.println("Superclass: " + clazz.getSuperclass().getName());
        System.out.println("Is array? " + clazz.isArray());
        System.out.println("Modifiers: " + Modifier.toString(clazz.getModifiers()));
    }
}
