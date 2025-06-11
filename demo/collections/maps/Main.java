import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        maps1();
    }

    public static void maps1(){
        Map<Integer, String> users = new HashMap<>();

        // .put()
        users.put(1,"Raddames");
        users.put(2,"James");
        users.put(3,"Carlos");

        System.out.println(users); // {1=Raddames, 2=James, 3=Carlos}
        //  .get()
        System.out.println(users.get(3)); // Carlos

        // .remove()
        users.remove(2); //removes james
        System.out.println(users);  // {1=Raddames, 3=Carlos}

        Map<Integer, List<String>> books = new HashMap<>();

         // Adding books to map
        books.put(1, new ArrayList<>(Arrays.asList("Book A", "Book B")));
        books.put(2, new ArrayList<>(Arrays.asList("Book C", "Book D")));
        books.put(3, new ArrayList<>(Arrays.asList("Book C", "Book D")));
        books.put(4, new ArrayList<>(Arrays.asList("Book D", "Book G", "Book T")));

        // Printing the map
        System.out.println(books);

        // .containsKey()
        System.out.println(books.containsKey(1)); // true
        // System.out.println(books.containsKey(6)); // false
        
        // To add another book to category 1
        books.get(1).add("Book E");
        
        

    }
}
