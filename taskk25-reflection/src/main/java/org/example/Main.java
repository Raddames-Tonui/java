package org.example;

import org.example.employee.Employee;
import org.example.employee.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws  Exception {
        /**
         * Load the employee class
         * You're dynamically loading the Employee class by name string, not by direct reference.
         * This is helpful when class names are known only at runtime (e.g., plugin systems, frameworks).
          */
        Class<?> empClass = Class.forName("org.example.employee.Employee");

        // Call a specific constructor with 4 parameters and instantiate an object via reflection.
        Object emp1 = empClass.getConstructor(int.class, String.class, String.class, String.class).newInstance(1, "Emp001", "James", "IT");

        // Calling getName() via reflection and casting the result because reflection returns Object
        String ls = (String) emp1.getClass().getMethod("getName").invoke(emp1);
        System.out.println(ls); // Should print James

        /**
         * Modify some fields with setters using reflection
         * You're now invoking setters via reflection
         * */
        empClass.getMethod("setName", String.class).invoke(emp1, "Bob");
        empClass.getMethod("setDepartment", String.class).invoke(emp1, "Hr");

        // Create object using 2-argument constructor
        Constructor<?> shortConstructor = empClass.getConstructor(int.class, String.class);
        Object emp2 = shortConstructor.newInstance(2, "Emp002");

        /**
            Display using utils class
            empClass is a Class<?> reference to the Employee class.
            This returns a Method object representing the static method.
        */
        Class<?> utilClass = Class.forName("org.example.employee.Utils");
        Method display = utilClass.getMethod("displayEmployeeDetails", empClass);


        System.out.println("-----------WITH REFLECTION-----------");
        /**
         invoke(...) is how you execute a method via reflection.
         The first argument is the target object. Since this is a static method, you pass null.
         The second argument is the actual parameter passed to the method—in this case, the Employee objects.
         */
        display.invoke(null, emp1);
        display.invoke(null, emp2);

        System.out.println("\n--------WITHOUT REFLECTION--------");
        withoutReflection();
    }

    private static void withoutReflection(){
        Employee emp1 = new Employee(1, "EMP001", "Alice", "IT");
        emp1.setName("Bob");
        emp1.setDepartment("Hr");

        Employee emp2 = new Employee(2, "Emp002");

        Utils.displayEmployeeDetails(emp1);
        Utils.displayEmployeeDetails(emp2);
    }

}
