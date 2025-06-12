import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Collections {
    public static void main(String[] args) {
        // listDemo();
        // setDemo();
        // listDemo2();
        streamsDemo();
    }

    // LISTS => ArrayLists, LinkedLists, vector
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
    // SETS => HashSet, TreeSet, LinkedHashSet
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

        // Iterator
        Iterator<Integer> iterator = set.iterator();
        System.out.println("Iterating through the set using Iterator:");
        while (iterator.hasNext()){
            Integer n = iterator.next();
            System.out.println("Iterator value: " + n);
            iterator.remove();
            System.out.println("Removed value: " + n);
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
        Set<Integer> set2 = new LinkedHashSet<>();
        set2.add(35);
        set2.add(10);
        set2.add(34);
        set2.add(25);
        set2.add(25); // Duplicate will not be added
        // System.out.println("Set2 of numbers (LinkedHashSet): " + set2);
        // for (Integer n : set2){
        //     System.out.println("Set2 value: " + n);
        // }

    
    }

    // LOOPING THROUGH A LIST
    // Using forEach, for loop, and index-based access
    public static void listDemo2 (){
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve"); 

        // Using forEach to iterate through the list
        names.forEach(n -> System.out.println(n));

        for (String name : names){
            System.out.println( name);
        }

        for (int i = 0; i < names.size(); i++){
            String name = names.get(i);
            System.out.println("Name at index " + i + ": " + name);
        }
    }
    
    public static void streamsDemo(){
        // Example of using streams to filter and collect names
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        // Using streams to filter names that start with 'A'
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A"))
                .toList();

        // System.out.println("Filtered names starting with 'A': " + filteredNames);


        List<Integer> nums = Arrays.asList(5, 3, 5, 20,16,76,33,48,96, 25);
        // Using streams to double each number and collect results
        List<Integer> doubleNumbers = nums.stream()
                .map(n -> n * 2)
                .toList();
        System.out.println("Original numbers: " + nums);
        System.out.println("Doubled numbers: " + doubleNumbers);

        // Using streams to find the sum of numbers
        int sum = nums.stream()
                .filter(n -> n%2 == 0) // Fileter even numbers
                .reduce(0, (c,e)->c + e); // Using reduce to sum the numbers 
        System.out.println("Sum of even numbers: " + sum);

    }

}