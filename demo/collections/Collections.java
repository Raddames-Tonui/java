import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Collections {
    public static void main(String[] args) {
        // listDemo();
        setDemo();
    }

    // LISTS
    // Ordered, indexed, allows duplicates
    public static void listDemo(){

            List<Integer> num = new ArrayList<Integer>();
            num.add(5);
            num.add(10);
            num.add(15);
            num.add(20);
            num.add(25);

            System.out.println("List of numbers: " + num);
            System.out.println("Size of the list: " + num.size());
            System.out.println("First element: " + num.get(0));
            System.out.println("Last element: " + num.get(num.size() - 1));
            System.out.println("Is the list empty? " + num.isEmpty());
            System.out.println("Does the list contain 15? " + num.contains(15));
            System.out.println("Index of 15: " + num.indexOf(15));
            System.out.println("Removing element at index 2: " + num.remove(2));
            System.out.println("List after removal: " + num);
            System.out.println("Clearing the list...");
            num.clear();
            System.out.println("Is the list empty after clearing? " + num.isEmpty());

            // Example: Double each element and print it
            for (Integer n : num){
                Integer doubled = n * 2;
                System.out.println("Doubled value: " + doubled);
            }
    }
    // SETS
    // Unordered, no index, no duplicates
    public static void setDemo(){
        // a) Using HashSet to demonstrate a set
        // Note: HashSet does not maintain order
        Set<Integer> set = new HashSet<Integer>();
        set.add(35);
        set.add(10);
        set.add(34);
        set.add(25);
        set.add(25);// Duplicate will not be added

        System.out.println("Set of numbers: " + set);

        for (Integer n : set){
            System.out.println("Set value: " + n);
        }

        // b) Using TreeSet to demonstrate a sorted set
        // Note: TreeSet maintains natural order
        Collection<Integer> set1 = new TreeSet<Integer>();
        set1.add(35);
        set1.add(10);
        set1.add(34);
        set1.add(25);
        set1.add(25); // Duplicate will not be added
        System.out.println("Set1 of numbers: " + set1);
        for (Integer n : set1){
            System.out.println("Set1 value: " + n);
        }

        // c) Using LinkedHashSet to maintain insertion order
        Set<Integer> set2 = new LinkedHashSet<Integer>();
        set2.add(35);
        set2.add(10);
        set2.add(34);
        set2.add(25);
        set2.add(25); // Duplicate will not be added
        System.out.println("Set2 of numbers (LinkedHashSet): " + set2);
        for (Integer n : set2){
            System.out.println("Set2 value: " + n);
        }

        
    }

    

}