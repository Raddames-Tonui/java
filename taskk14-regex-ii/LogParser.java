import java.io.*;
import java.util.regex.*;

public class LogParser {
    public static void main(String[] args) {
        String logData = """
            [2025-01-06 14:32:10] INFO User: JohnDoe, Action: Login successful
            [2025-01-06 14:33:22] ERROR User: JaneDoe, Action: Invalid password
            [2025-01-07 14:34:15] INFO User: JohnDoe, Action: Viewed profile
            [2025-01-07 14:35:15] INFO User: MaryDoe, Action: Viewed profile
            [2025-01-07 14:35:18] INFO User: JohnDoe, Action: Viewed profile
            [2025-01-07 14:35:42] ERROR User: JohnDoe, Action: Unauthorized access attempt
            [2025-01-07 14:36:00] INFO User: JaneDoe, Action: Logout successful
            [2025-01-07 14:37:00] INFO User: JohnDoe, Action: Logout successful
            [2025-01-08 14:38:00] ERROR User: JohnDoe, Action: Invalid password
        """.stripIndent();

        System.out.println("--- ERROR Logs for 6th and 8th January 2025 ---");
        Pattern errorPattern = Pattern.compile("\\[(2025-01-06|2025-01-08) \\d{2}:\\d{2}:\\d{2}\\] ERROR .+");
        Matcher errorMatcher = errorPattern.matcher(logData);
        while (errorMatcher.find()) {
            System.out.println(errorMatcher.group());   
        }

        System.out.println("\n--- Logs with Action Containing 'password' (Lookahead) ---");
        Pattern passwordPattern = Pattern.compile(".*(?=Action: .*password).*", Pattern.CASE_INSENSITIVE);
        Matcher passwordMatcher = passwordPattern.matcher(logData);
        while (passwordMatcher.find()) {
            System.out.println(passwordMatcher.group());
        }

        System.out.println("\n--- Consecutive Repeated Actions (Backreference) ---");
        Pattern repeatedPattern = Pattern.compile("(?m)^.*Action: (.+)\\R^.*Action: \\1.*$");
        Matcher repeatedMatcher = repeatedPattern.matcher(logData);
        while (repeatedMatcher.find()) {
            System.out.println(repeatedMatcher.group());
        }

        System.out.println("\n--- Extracted and Formatted Logs Using Named Groups ---");
        Pattern namedGroupsPattern = Pattern.compile(
                "\\[(?<timestamp>\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})\\] (?<level>INFO|ERROR) User: (?<user>\\w+), Action: (?<action>.+)");
        Matcher namedGroupsMatcher = namedGroupsPattern.matcher(logData);
        while (namedGroupsMatcher.find()) {
            System.out.println("Timestamp: " + namedGroupsMatcher.group("timestamp"));
            System.out.println("Level    : " + namedGroupsMatcher.group("level"));
            System.out.println("User     : " + namedGroupsMatcher.group("user"));
            System.out.println("Action   : " + namedGroupsMatcher.group("action"));
            System.out.println("-----------------------------------------");
        }
    }
}