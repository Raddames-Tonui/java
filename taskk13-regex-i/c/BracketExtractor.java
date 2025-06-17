import java.io.*;
import java.util.regex.*;

public class BracketExtractor {
    public static void main(String[] args) throws IOException {
        File file = new File("workflow-approval.html");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        Pattern pattern = Pattern.compile("\\[[^\\]]+\\]");

        System.out.println("Variables in brackets:");

        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }
        reader.close();
    }
}
