import java.util.Arrays;
import java.util.List;

import generics.*;
import extras.*;


// Main Test
public class Main {
    public static void main(String[] args) {
        // ✅ 1. Item<T>
        Item<String> stringItem = new Item<>("Java Generics");
        Item<Integer> intItem = new Item<>(2025);
        
        stringItem.displayItem();
        intItem.displayItem();

        System.out.println();

        // ✅ 2. printArrayContents(T[] array)
        String[] names = {"Alice", "Bob", "Charlie"};
        Integer[] numbers = {10, 20, 30};

        System.out.println("String Array:");
        GenericUtils.printArrayContents(names);

        System.out.println("Integer Array:");
        GenericUtils.printArrayContents(numbers);
        System.out.println();

        // ✅ 3. DataHandler<T> interface
        DataHandler<String> stringHandler = new StringHandler();
        DataHandler<Integer> intHandler = new IntegerHandler();
        stringHandler.processData("helloWorld");
        intHandler.processData(4);
        intHandler.processData(0);
        System.out.println();

        // ✅ 4. Calculator<T extends Number>
        Calculator<Integer> squareCalc = new Calculator<>(5);
        squareCalc.displaySquare();
        System.out.println();
        Calculator<Double> circleCalc = new Calculator<>(7.0);
        circleCalc.displayCircle();
        System.out.println();
        Calculator<Float> bothCalc = new Calculator<>(4.5f);
        bothCalc.displaySquare();
        bothCalc.displayCircle();
        System.out.println();

        // ✅ 5. Wildcards <? extends T>
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        WildcardPrinter.printNumberList(doubleList);

         // ✅ 6. Create a Fluent object for String
        // Method chaining allows setting multiple properties in one line
        Fluent<String> fluentString = new Fluent<String>()
            .set("Hello, Fluent World!")  // Set value
            .label("Greeting");           // Set label

        fluentString.display(); // Output: Greeting: Hello, Fluent World!

        // Create a Fluent object for Integer
        Fluent<Integer> fluentInt = new Fluent<Integer>()
            .set(42)              // Set integer value
            .label("The Answer"); // Set label

        fluentInt.display(); // Output: The Answer: 42
    }
}
