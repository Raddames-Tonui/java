import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivateScanner {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java PrivateScanner <file path>");
            return;
        }

        String filePath = args[0];
        File file = new File(filePath);

        // Try-with-resources ensures reader is closed safely
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            // Regex: Match private fields like 'private int count;'
            Pattern fieldPattern = Pattern.compile("private\\s+\\w+\\s+\\w+\\s*;");

            // Regex: Match private methods like 'private void run() {'
            Pattern methodPattern = Pattern.compile("private\\s+\\w+\\s+\\w+\\s*\\([^)]*\\)\\s*\\{?");

            //     // Match fields with optional access modifier (default, private, public, protected)
            // Pattern fieldPattern = Pattern.compile("\\b(private|public|protected)?\\s*\\w+\\s+\\w+\\s*;");

            // // Match methods with optional access modifier
            // Pattern methodPattern = Pattern.compile("\\b(private|public|protected)?\\s*\\w+\\s+\\w+\\s*\\([^)]*\\)\\s*\\{?");

            System.out.println("------ Private Fields and Methods:-----------");

            while ((line = reader.readLine()) != null) {
                Matcher fieldMatcher = fieldPattern.matcher(line);
                Matcher methodMatcher = methodPattern.matcher(line);

                if (fieldMatcher.find()) {
                    System.out.println("Field:  " + fieldMatcher.group());
                }
                if (methodMatcher.find()) {
                    System.out.println("Method: " + methodMatcher.group());
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
