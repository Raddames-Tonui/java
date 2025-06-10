import java.util.*;

public class maps {
    public static void main(String[] args) {
        mapDemo1();
      
    }

    public static void mapDemo(){
          // Example usage of Map
        Map<Integer, String> map = new HashMap<>();
        
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

    public static void mapDemo1(){
        Map<String, String> student = new HashMap<>();
        
        // Adding elements to the map
        student.put("name", "Joe Doe");
        student.put("age", "20");
        student.put("major", "Computer Science");
        student.put("year", "2nd Year");

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
