package extras;

import java.util.List;

// Wildcard Usage
public class WildcardPrinter {
    public static void printNumberList(List<? extends Number> list) {
        System.out.println("Wildcard Number List:");
        for (Number n : list) {
            System.out.println(n);
        }
    }
}