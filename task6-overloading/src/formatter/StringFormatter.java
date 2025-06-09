package formatter;

public class StringFormatter {

    public String format(String input) {
        String[] words = input.trim().split("\\s+");
        StringBuilder formatted = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                formatted.append(Character.toUpperCase(word.charAt(0)))
                         .append(word.substring(1).toLowerCase())
                         .append(" ");
            }
        }
        return formatted.toString().trim();
    }

    public String format(String input, int repeat) {
        return input.repeat(Math.max(0, repeat));
    }

    public String format(String input, String prefix, String suffix) {
        return prefix + input + suffix;
    }
}
