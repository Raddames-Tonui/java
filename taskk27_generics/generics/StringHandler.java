package generics;

// String Handler
public class StringHandler implements DataHandler<String> {
    @Override
    public void processData(String data) {
        String result = new StringBuilder(data).reverse().toString().toUpperCase();
        System.out.println("Processed String: " + result);
    }
}