package taskk27_Generics.a;

// Generic Class
public class Item <T>{
    private T value;

//    Constructor
    public Item(T value){
        this.value = value;
    }

    public T getValue(){
        return this.value;
    }

    public void setValue(T value){
        this.value = value;
    }

//    Display
/*
value.getClass() returns the runtime class of the object.
.getSimpleName() returns just the class name without the package (e.g., String, Integer).
 */

    public void display() {
        System.out.println("Item contains: " + value);
        System.out.println("Type of T is: " + value.getClass().getSimpleName());
    }

}
