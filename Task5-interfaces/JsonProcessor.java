
import org.json.JSONObject;
import java.util.Iterator;


public class JsonProcessor implements DataProcessor {

    @Override
    public void process(String data){
        try {
            // Parse the JSON data
            // new JSONObject(data): Converts the input string into a JSONObject instance.
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();

            System.out.println("Processing JSON data: ");
            // Iterate through the keys in the JSON object
            while (keys.hasNext()){
                String key = keys.next();
                Object value = json.get(key);
                System.out.println("Key: " + key + ", Value: " + value);
            } 
        }catch (Exception e){
                System.out.println("Invalid JSON data: " + e.getMessage());
            }

    };
    
}
