import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Hello");
        Matcher matcher = pattern.matcher("Heello");

        boolean found = matcher.find();

        System.out.println("found: " +found);

    }
}
