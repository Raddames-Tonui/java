# Understanding Java Annotations

## 📘 What Are Annotations?

Annotations in Java are **metadata** – information about the code, but not the code itself. They don't directly affect how your code runs, but they can influence how tools, compilers, or frameworks handle your code.

### 🧒 Simple Explanation 

Think of annotations like **stickers** you put on your notebooks. Some stickers say “Homework,” “Important,” or “Fun.” These stickers tell the teacher or you what to do with each notebook. Annotations are like that — they tell Java tools what to do with the class or method they’re stuck to.

---

## 🧑‍💻 Professional-Level Explanation

Annotations provide a mechanism for:

* Compiler instructions
* Compile-time and runtime processing
* Framework metadata configuration (e.g., Spring, JPA)

They are declared using `@interface` and used with the `@` symbol.

---

## 🧱 Anatomy of an Annotation

### Declaring a Custom Annotation

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String value();
    int priority() default 1;
}
```

### Explanation:

* `@Retention` – Defines how long annotation metadata is kept (e.g., SOURCE, CLASS, RUNTIME)
* `@Target` – Indicates where the annotation can be applied (e.g., METHOD, FIELD, TYPE)
* `@interface` – Keyword to declare an annotation
* Members inside behave like methods, and can have default values

---

## 🧪 Using Annotations

```java
public class Demo {
    @MyAnnotation(value = "Hello", priority = 2)
    public void greet() {
        System.out.println("Hello World");
    }
}
```

## 🔍 Processing Annotations with Reflection

```java
Method method = Demo.class.getMethod("greet");
MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
System.out.println(annotation.value());  // Outputs: Hello
System.out.println(annotation.priority()); // Outputs: 2
```

---


## 📦 Common Built-in Annotations

| Annotation             | Purpose                                                               |
| ---------------------- | --------------------------------------------------------------------- |
| `@Override`            | Ensures the method overrides a method from a superclass               |
| `@Deprecated`          | Marks code as obsolete; compiler issues a warning if used             |
| `@SuppressWarnings`    | Suppresses compiler warnings for unchecked operations, etc.           |
| `@FunctionalInterface` | Specifies that the interface is intended to be a functional interface |
| `@SafeVarargs`         | Suppresses warnings about varargs usage with generics                 |
| `@Native`              | Indicates a constant can be referenced by native code                 |
| `@Repeatable`          | Enables repeating the same annotation on one element                  |
| `@Documented`          | Includes the annotation in the generated Javadoc                      |
| `@Target`              | Specifies the element types an annotation can be applied to           |
| `@Retention`           | Specifies how long annotations are retained                           |
| `@Inherited`           | Lets an annotation be inherited by subclasses                         |

---

## 🛠️ Annotation Meta-Annotations

| Meta-Annotation | Description                                       |
| --------------- | ------------------------------------------------- |
| `@Retention`    | Specifies how long the annotation is retained     |
| `@Target`       | Specifies where the annotation can be applied     |
| `@Documented`   | Includes in Javadoc                               |
| `@Inherited`    | Allows child classes to inherit parent annotation |

---

## ✅ Key Takeaways

* Annotations are metadata, not logic.
* Use built-in ones like `@Override`, or create custom ones.
* You can process them at **runtime** using **reflection**.
* They're widely used in frameworks (like Spring, Hibernate).



