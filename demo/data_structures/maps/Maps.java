import java.util.*;

public class Maps {
    public static void main(String[] args) {

        System.out.println("-----------HASH MAP---------");
        hashMapDemo();

        System.out.println("\n-----LINKED HASH MAP---------");
        linkedHashMap();


        System.out.println("\n --------TREE MAP---------");
        treeMapDemo();



        // mapDemo1();
      
    }

    public static void hashMapDemo(){
        HashMap<Integer, String> names = new HashMap<>();
        names.put(1, "Raddames");
        names.put(2,"James");
        names.put(3, "Hellen");
        names.put( null, "billy");
        names.put(67, null);
        names.put(null, "Johny");
        names.put(6, null);



        System.out.println(names);
        System.out.println(names.get(1));
        System.out.println(names.containsKey(3));
        System.out.println(names.containsValue("Hellen"));

        names.replace(3, "Job");
        names.replace(5,"buddy");
        System.out.println(names);

        names.put(2, "biggy");
        names.putIfAbsent(1,"davy");
        System.out.println(names);

        names.putIfAbsent(65, "Yellow");
        System.out.println(names);
    }

    public static void linkedHashMap(){
  // Example usage of Map
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        
        // Adding elements to the map
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        
        // Displaying the map
        System.out.println("Map contents: " + map);
        
        // Accessing an element
        String value = map.get(2);
        System.out.println("Value for key 2: " + value);
        
        // Checking if a key exists
        boolean hasKey = map.containsKey(3);
        System.out.println("Does key 3 exist? " + hasKey);
        
        // Removing an element
        map.remove(1);
        System.out.println("Map after removing key 1: " + map);
        
        // Iterating through the map
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public static void treeMapDemo(){
        Map<String, String> student = new TreeMap<>();
        
        // Adding elements to the map
        student.put("name", "Joe Doe");
        student.put("age", "20");
        student.put("major", "Computer Science");
        student.put("year", "2ndYear");

        // Displaying the map
        System.out.println("Student details: "+ student);

        // Accessing an element
        String name = student.get("name");
        System.out.println("Student Name: " + name);

        // Looping through the map
        System.out.println("Iterating through the student map:");
        // Using keySet() to get keys and then accessing values
        for (String key : student.keySet()){
            String value = student.get(key);
            System.out.println( key + " : "+ value);
        }
        System.out.println("Only keys : "+ student.keySet());
        System.out.println("Only values : "+ student.values());
    }
    
}
