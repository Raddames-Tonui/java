package taskk27_Generics.c;

// Class to handle String type data
public class StringHandler implements DataHandler<String> {

    @Override
    public void processData(String data){
//        Reverse the string and convert to uppercase
        String reversed =  new StringBuilder(data).reverse().toString().toUpperCase();
        System.out.println("Reversed String: "+ reversed);
    }


}

