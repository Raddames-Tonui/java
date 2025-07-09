package org.example.b;

public class ArrayPrinter {

    // Generic method that prints the contents of any array
    public static <T> void printArrayContents(T[] array) {
        System.out.println("Array Contents:");
        for (T element : array){
            System.out.println(element);
        }

    }
}
