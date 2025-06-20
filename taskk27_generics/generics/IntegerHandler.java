package generics;



// Integer Handler
public class IntegerHandler implements DataHandler<Integer> {
    @Override
    public void processData(Integer data) {
        if (data == 0) {
            System.out.println("Cannot compute reciprocal of zero.");
        } else {
            System.out.println("Reciprocal: " + (1.0 / data));
        }
    }
}