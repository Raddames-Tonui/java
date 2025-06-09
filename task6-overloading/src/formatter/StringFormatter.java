package formatter;

public class StringFormatter {

    public void display() {
        System.out.println("===========STRING FORMATTER CLASS===============");
    }

    /**
     * Capitalizes the first letter of each word in the input string
     * and converts the rest of the letters in each word to lowercase.
     */
    public String format(String input) {
        display();

        // Handle null or empty input by returning empty string
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Split the input string by whitespace(s) into words
        String[] words = input.trim().split("\\s+");
        StringBuilder formatted = new StringBuilder();

        // Capitalize each word and append it to the result
        for (String word : words) {
            if (!word.isEmpty()) {
                formatted.append(Character.toUpperCase(word.charAt(0)))  // Capitalize first letter
                         .append(word.substring(1).toLowerCase())        // Lowercase the rest
                         .append(" ");                                   // Add space between words
            }
        }

        // Trim trailing space and return the formatted string
        return formatted.toString().trim();
    }

    
    //  * Repeats the input string a specified number of times.
    public String format(String input, int repeat) {
        return input.repeat(Math.max(0, repeat));
    }

    /**
     * Wraps the input string with a given prefix and suffix.
     * 
     * @param input  The string to be wrapped.
     * @param prefix The prefix to prepend.
     * @param suffix The suffix to append.
     */
    public String format(String input, String prefix, String suffix) {
        return prefix + input + suffix;
    }
}
