package taskk25_Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionApp {
    public static void main(String[] args) throws Exception {

//        Load the empoyee class
        Class<?> empClass = Class.forName("taskk25_Reflection.employee.Employee");

//        Create full object using 4-argument constructor
        Object emp1 = empClass.getConstructor(int.class, String.class, String.class, String.class).newInstance(1, "EMP001", "Alice", "IT");

        String ls  = (String) emp1.getClass().getMethod("getName").invoke(emp1);
        System.out.println(ls);  // Should display Alice

//        Modify some fields with setters using reflection
        empClass.getMethod("setName", String.class).invoke(emp1, "Bob");
        empClass.getMethod("setDepartment", String.class).invoke(emp1, "Hr");

//        Create object using 2-argument constructor
        Constructor<?> shortConstructor = empClass.getConstructor(int.class, String.class);
        Object emp2 = shortConstructor.newInstance(2, "Emp002");

//        Display using utils class
        /*
            empClass is a Class<?> reference to the Employee class.
            This returns a Method object representing the static method.
        */
        Class<?> utilsClass = Class.forName("taskk25_Reflection.utils.Utils");
        Method display = utilsClass.getMethod("displayEmployeeDetails", empClass);

        /*
            invoke(...) is how you execute a method via reflection.
            The first argument is the target object. Since this is a static method, you pass null.
            The second argument is the actual parameter passed to the method—in this case, the Employee objects.
        */
       display.invoke(null, emp1);
       display.invoke(null, emp2);
    }

}
