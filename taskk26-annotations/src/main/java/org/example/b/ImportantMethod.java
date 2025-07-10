package org.example.b;

import java.lang.annotation.*;

// Annotation to mark critical methods
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
//@Repeatable()
public @interface ImportantMethod {
}
