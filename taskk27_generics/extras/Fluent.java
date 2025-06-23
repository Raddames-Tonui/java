package extras;

// A generic class that supports method chaining
  // Method chaining allows setting multiple properties in one line
public class Fluent<T> {
    private T value;
    private String label;

    // Sets the value and returns 'this' for chaining
    public Fluent<T> set(T val) {
        this.value = val;
        return this; // Enable chaining: fluent.set(...).label(...)
    }

    // Sets the label and returns 'this' for chaining
    public Fluent<T> label(String lbl) {
        this.label = lbl;
        return this; // Enable chaining: fluent.set(...).label(...)
    }

    // Returns the value
    public T get() {
        return value;
    }

    // Displays label + value (e.g., "Title: Value")
    public void display() {
        System.out.println(label + ": " + value);
    }
}
