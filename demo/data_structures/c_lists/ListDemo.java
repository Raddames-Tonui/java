import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {

        System.out.println("-------------List----------------- ");
        ListDemo1();

        System.out.println("\n -------------ArrayList------------ ");     
        ArrayListDemo();

        System.out.println("\n -------------LinkedList----------- ");
        LinkedListDemo();
    }

    public static void ListDemo1(){
        List<String> names  = new ArrayList<>();
        names.add("James");
        names.add("zacheus");
        names.add(2, "paul");
        names.add("Nancy");

        System.out.println(names); //[James, Zacheus, paul, Nancy]
        
        // Access
        String name = names.get(2);
        String firstName = names.getFirst();
        System.out.println(name +" "+ firstName); //paul James

        // Modify
        names.set(3, "pinky");
        System.out.println(names);// [James, Zacheus, paul, pinky]

        // Remove
        names.remove(0);
        System.out.println(names); //[Zacheus, paul, pinky]

        // size
        int size = names.size();
        System.out.println(size); // 3

        // Sort
        Collections.sort(names);
        System.out.println(names); //[paul, pinky, zacheus]
    }

    public static void ArrayListDemo(){
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("bananas");
        fruits.add("mangoes");
        fruits.add("eggs");
        fruits.add("apples");

        System.out.println(fruits); // [bananas, mangoes, eggs, apples]

        fruits.add(1, "tomatoes");
        System.out.println(fruits); // [bananas, tomatoes, mangoes, eggs, apples]

        String f= fruits.get(4);
        System.out.println(f); // apples
        
        fruits.set(1, "guavas");
        System.out.println(fruits); // [bananas, guavas, mangoes, eggs, apples]

        fruits.remove(3);
        System.out.println(fruits); // [bananas, guavas, mangoes, apples]

        System.out.println(fruits.contains("eggs")); // false

        System.out.println(fruits.size()); // 4

        fruits.clear();
        System.out.println(fruits); // []

        System.out.println(fruits.isEmpty()); //true




    }

    public static void LinkedListDemo(){
        LinkedList<String> history = new LinkedList<>();

        history.add("Home Page");
        history.add("About Us");
        history.add("Contact");

        System.out.println("Current: " + history.getLast());

        // Simulate going back
        String lastVisited = history.removeLast();
        System.out.println("Going back from: " + lastVisited);
        System.out.println("Now at: " + history.getLast());

        // Simulate going forward again
        history.addLast(lastVisited);
        System.out.println("Going forward to: " + history.getLast());
    
    }
    
}
