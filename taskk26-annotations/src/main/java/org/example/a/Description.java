package org.example.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Custom annotation to be applied to fields

@Retention(RetentionPolicy.RUNTIME) // Keep annotation at runtime for reflection
@Target(ElementType.FIELD) // Only applied to fields
public @interface Description{
    String info() default "Hello world";
}