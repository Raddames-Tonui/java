import java.util.*;

public class SetsDemo {
    public static void main(String[] args) {
        System.out.println("\n--------------- hashSet --------------");
        hashSetDemo();

        System.out.println("\n--------------- linkedHashSet --------------");
        linkedHashSetDemo();

        System.out.println("\n--------------- sortedSet (via TreeSet) --------------");
        sortedSetDemo();

        System.out.println("\n--------------- treeSet (with NavigableSet features) --------------");
        treeSetDemo();
    }

    public static void hashSetDemo() {
        Set<Integer> hashSet = new HashSet<>(Arrays.asList(34, 12, 9, 34, 56, 12));
        System.out.println("Initial contents (unordered, no duplicates): " + hashSet); // [34, 9, 12, 56]
        
        hashSet.add(100);
        System.out.println("After adding 100: " + hashSet); // [34, 100, 9, 12, 56]

        System.out.println("Contains 56? " + hashSet.contains(56)); // true

        hashSet.remove(9);
        System.out.println("After removing 9: " + hashSet); // [34, 100, 12, 56]

        System.out.println("Set size: " + hashSet.size()); // 4

        hashSet.clear();
        System.out.println("After clearing: " + hashSet); // []
    }

    public static void linkedHashSetDemo() {
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("User123");
        linkedSet.add("Admin42");
        linkedSet.add("Guest99");
        linkedSet.add("Admin42"); // duplicate

        System.out.println("Insertion order maintained: " + linkedSet); // [User123, Admin42, Guest99]

        System.out.println("Contains 'Guest99'? " + linkedSet.contains("Guest99")); // true

        linkedSet.remove("User123");
        System.out.println("After removing 'User123': " + linkedSet); // [Admin42, Guest99]

        System.out.println("Is set empty? " + linkedSet.isEmpty()); // false
    }

    public static void sortedSetDemo() {
        SortedSet<String> names = new TreeSet<>();
        names.addAll(Arrays.asList("Daniel", "Brian", "Alice", "Eve", "Charlie", "Alice")); // Duplicate "Alice"

        System.out.println("Sorted names: " + names); // [Alice, Brian, Charlie, Daniel, Eve]

        System.out.println("First: " + names.first()); // Alice
        System.out.println("Last: " + names.last()); // Eve

        System.out.println("HeadSet(before 'Daniel'): " + names.headSet("Daniel")); // [Alice, Brian, Charlie]
        System.out.println("TailSet(from 'Charlie'): " + names.tailSet("Charlie")); // [Charlie, Daniel, Eve]

        System.out.println("SubSet (Brian to Daniel): " + names.subSet("Brian", "Daniel")); // [Brian, Charlie]
    }
    public static void navigableSetDemo(){
        NavigableSet<Double> scores = new TreeSet<>();
        scores.addAll(Arrays.asList(88.5, 72.3, 95.0, 88.5, 60.0, 79.8));

        System.out.println("Sorted scores: " + scores); // [60.0, 72.3, 79.8, 88.5, 95.0]

        System.out.println("Lower than 88.5: " + scores.lower(88.5)); // 79.8
        System.out.println("Higher than 72.3: " + scores.higher(72.3)); // 79.8
        System.out.println("Floor of 90.0: " + scores.floor(90.0)); // 88.5
        System.out.println("Ceiling of 75.0: " + scores.ceiling(75.0)); // 79.8

        System.out.println("Descending set: " + scores.descendingSet()); // [95.0, 88.5, 79.8, 72.3, 60.0]

        System.out.println("Poll First (lowest): " + scores.pollFirst()); // 60.0
        System.out.println("Poll Last (highest): " + scores.pollLast()); // 95.0

        System.out.println("Remaining scores: " + scores); // [72.3, 79.8, 88.5]
    }
    }
    public static void treeSetDemo() {

}
