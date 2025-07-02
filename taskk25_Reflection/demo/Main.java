package taskk25_Reflection.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Cat myCat = new Cat("Stella", 12);
        myCat.meowMethod();

        System.out.println("-----------Original cat Details---------- \n" + myCat.getName()+ "\n" + myCat.getAge());
        
        // Get declared fields
        Field[] catFields = myCat.getClass().getDeclaredFields();
        System.out.println("\n-----------Cat as the following fields-------");
        for (Field field : catFields){
            if(field.getName().equals("name")){
                field.setAccessible(true); // Set to true so as to allow modification
                field.set(myCat, "Jimmy" );
            }
            // Display field names in cat
            System.out.println(field.getName());
        }
        // The private name should be changed using reflection
        System.out.println("\nCat name has been changed: " + myCat.getName());

//        Get declared Methods
        Method[] catMethods = myCat.getClass().getDeclaredMethods();
        System.out.println("\n-----------Cat as the following methods-------");
        for (Method methods : catMethods) {
            System.out.println(methods.getName());

        }

        System.out.println("\n-------Invoking Methods by Reflection-------");
        for (Method methods : catMethods){
            if (methods.getName().equals("meowMethod")){
                methods.setAccessible(true);
                methods.invoke(myCat); // meowMethod is a public method and this is useless since you can just invoke myCat.meowMethod();
            }if (methods.getName().equals("thisIsPrivateMethod")){
                methods.setAccessible(true);
                methods.invoke(myCat); // You have to pass object to invoke the private method,
            }
            if (methods.getName().equals("thisIsPublicStaticMethod")){
                methods.setAccessible(true);
                methods.invoke(null); // In static methods you invoke by passing a null unlike above private method.
            } if (methods.getName().equals("thisIsPrivateStaticMethod")){
                methods.setAccessible(true);
                methods.invoke(null); // In static methods you invoke by passing a null unlike above.
            }
        }
    }
}
