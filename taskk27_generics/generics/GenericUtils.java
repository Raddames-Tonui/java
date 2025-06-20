package generics;



// Generic Method
public class GenericUtils {
    public static <T> void printArrayContents(T[] array) {
        for (T item : array) {
            System.out.println(item);
        }

         // for (int i = 0; i < array.length; i++){
        //     System.out.println(array[i]);
        // }
    }
}