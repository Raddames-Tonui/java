package taskk26_Annotations.c;

import java.lang.reflect.Field;


// Class + field metadata via enums
public class Main {
    public static void main(String[] args) {
        // Scan classe and print annotated field metadata
        System.out.println("\n------Subject Fields-------");
        scanEntity(Subject.class);

        System.out.println("\n------Pupil Fields-------");
        scanEntity(Pupil.class);

        System.out.println("\n------Exam Fields-------");
        scanEntity(Exam.class);
    }

    // Generic method to scan any class annotated with @EntityInfo
    public static void scanEntity(Class<?> clazz){
        // Check if the class itself is annotated with @EntityInfo

 /*
        In Java, .class is a class literal — it refers to the Class object of a specific type at runtime.
        Example: EntityInfo.class
        EntityInfo is an annotation interface.
        EntityInfo.class gives you the Class object that represents the annotation EntityInfo.

        Subject.class	    -- Class object for the Subject class
        EntityInfo.class	-- Class object for the EntityInfo annotation
        someObj.getClass()	-- Gets Class object from an instance
*/
        if (clazz.isAnnotationPresent(EntityInfo.class)) {
            EntityInfo entityInfo = clazz.getAnnotation(EntityInfo.class);

            // Print table name and label from annotation
            System.out.println("Table: " + clazz.getSimpleName());
            System.out.println("Label: " + entityInfo.label());

            // Loop through all declared fields in the class
            for (Field field : clazz.getDeclaredFields()) {
                if(field.isAnnotationPresent(EntityInfo.class)) {
                    EntityInfo fieldInfo = field.getAnnotation(EntityInfo.class);

                    // Print field name, label, type, and datatype
                    System.out.println(field.getName() + ": " + fieldInfo.label() +
                            " (" + fieldInfo.entityType() + ", " + fieldInfo.dataType() + ")");
                }
            }
        }
    }
}
