package taskk27_Generics;

import taskk27_Generics.a.Item;
import taskk27_Generics.a.KeyValue;
import taskk27_Generics.b.ArrayPrinter;
import taskk27_Generics.c.DataHandler;
import taskk27_Generics.c.IntegerHandler;
import taskk27_Generics.c.StringHandler;

public class Main {
    public static void main(String[] args) {
    //  generic class Item<T>
    Item<String> stringItem = new Item<>("Java");
    Item<Integer> integerItem = new Item<>(2000);

        System.out.println("\n----------Generic Class---------");
    stringItem.display();
    integerItem.display();

//  generic method printArrayContents
    String[] words = {"Sons", "Of", "Zebedee", };
    Integer[] numbers = {1,3,2,5,15,23};

        System.out.println("\n---------Generic Arrays-----------");
    ArrayPrinter.printArrayContents(words);
    ArrayPrinter.printArrayContents(numbers);

//    generic interface and its implementations
        DataHandler<String> stringHandler = new StringHandler();
        System.out.println("\n--------Generic Interface---------");
        stringHandler.processData("hello raddames!");

        DataHandler<Integer> integerDataHandler = new IntegerHandler();
        integerDataHandler.processData(4);
        integerDataHandler.processData(0);


//        Key Value
        KeyValue<String, Integer> kv1 = new KeyValue<>("age" , 30);
        KeyValue<Integer, String> kv2 = new KeyValue<>(101, "User101");

        System.out.println("\n--------Generic KEY VALUE--------");
        kv1.display();
        kv2.display();
    }
}
