package org.example.a;

import java.lang.reflect.Field;

// Field annotations, runtime reflection
public class Main {
    public static void main(String[] args) {
        Class<Employee>  employeeClass = Employee.class;

        for (Field field : employeeClass.getDeclaredFields()) {
//            Check if the field has the @Description annotation
            if (field.isAnnotationPresent(Description.class)) {
                Description description = field.getAnnotation(Description.class);
                System.out.println(field.getName() + ": " + description.info());
            }
        }
    }
}
