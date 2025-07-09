package org.example.c;


//class to handle integer
public class IntegerHandler implements DataHandler<Integer> {
    @Override
    public void processData(Integer data){
        if (data == 0){
            System.out.println("Cannot divide by zero");
            return;
        }
//        Calculate reciprocal
        double reciprocal = 1.0 / data;
        System.out.println("Reciprocal: "+ reciprocal);
    }
}