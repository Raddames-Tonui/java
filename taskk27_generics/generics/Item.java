package generics;

// Generic Class
public class Item<T> {
    private T value;
    
    public Item(T value) { 
        this.value = value; 
    }

    public T getValue() {
         return value; 
    }

    public void setValue(T value) {
         this.value = value; 
    }
    public void displayItem() {
        System.out.println("Item: " + value);
    }
}