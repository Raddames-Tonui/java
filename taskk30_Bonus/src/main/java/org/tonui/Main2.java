package org.tonui;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class Main2 {

    // 🧍 Model class representing a member's personal info
    static class Member {
        String id;
        String name;
        String mobile;
        String email;
        String gender;

        // 👇 Constructor that also sanitizes input by removing whitespace and nulls
        public Member(String id, String name, String mobile, String email, String gender) {
            // If any field is null, default to empty string. Then trim surrounding whitespace.
            this.id = id == null ? "" : id.trim(); // Removes leading/trailing whitespace from ID
            this.name = name == null ? "" : name.trim(); // Removes whitespace around name
            this.mobile = mobile == null ? "" : mobile.trim().replaceAll("\\s+", "");
            // `replaceAll("\\s+", "")`: removes **all** internal whitespace (e.g., "07 11 223344" → "0711223344")

            this.email = email == null ? "" : email.trim().replaceAll("\\s+", "");
            // `replaceAll("\\s+", "")`: emails shouldn't contain spaces — strips accidental typing errors

            this.gender = gender == null ? "" : gender.trim(); // Clean up the gender label
        }

        // ✅ Checks if the ID is valid: only digits, 6 to 12 characters long
        public boolean isValidID() {
            return id.matches("^\\d{6,12}$");
            // ^     → start of the string
            // \\d   → any digit (0–9)
            // {6,12} → must be between 6 and 12 digits
            // $     → end of the string
        }

        // ✅ Checks if the mobile number is valid (Kenyan formats + generic support)
        public boolean isValidMobile() {
            return mobile.matches("^(07\\d{8}|2547\\d{8}|\\d{10,13})$");
            // 07\\d{8}   → Kenyan mobile format e.g., 0712345678
            // 2547\\d{8} → International format e.g., 254712345678
            // \\d{10,13} → fallback: 10–13 digit numbers (e.g., no country code or with)
        }

        // ✅ Checks if email is in valid format using basic RFC-compliant regex
        public boolean isValidEmail() {
            return email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
            // ^                       → start of email
            // [\\w._%+-]+             → username part: alphanumeric, dot, underscore, etc.
            // @                       → mandatory '@'
            // [\\w.-]+                → domain (e.g., gmail, outlook)
            // \\.                     → literal dot '.'
            // [a-zA-Z]{2,}            → top-level domain: must be at least 2 letters (e.g., "com", "org")
            // $                       → end of string
        }

        // ✅ Returns true if all individual validators pass
        public boolean isValid() {
            return isValidID() && isValidMobile() && isValidEmail();
        }
    }

    public static void main(String[] args) throws Exception {
        // ⏱ Start measuring total execution time
        Instant start = Instant.now();

        // 📂 Get the full path to the CSV file stored in resources folder
        Path path = Paths.get(ClassLoader.getSystemResource("member_details.csv").toURI());

        // 🧵 Prepare thread-safe lists to hold members grouped by gender or invalidity
        List<Member> maleList = Collections.synchronizedList(new ArrayList<>());
        List<Member> femaleList = Collections.synchronizedList(new ArrayList<>());
        List<Member> unknownList = Collections.synchronizedList(new ArrayList<>());
        List<Member> invalidList = Collections.synchronizedList(new ArrayList<>());

        // ⚡ Create a thread pool based on available CPU cores to speed up processing
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // 🧾 Read file line by line
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String header = br.readLine(); // Skip the first line (CSV headers)
            List<Callable<Void>> tasks = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                final String record = line; // Each record is one CSV row (excluding header)

                // 💡 Wrap record handling as a task for multi-threaded execution
                tasks.add(() -> {
                    String[] fields = record.split(",", -1); // -1 keeps empty strings instead of ignoring them
                    if (fields.length < 5) return null; // Skip lines with missing columns

                    // 🎯 Sanitize, clean, and validate the data
                    Member m = new Member(fields[0], fields[1], fields[2], fields[3], fields[4]);

                    if (!m.isValid()) {
                        invalidList.add(m); // Put in invalid bucket
                    } else {
                        // Group by gender
                        switch (m.gender.toLowerCase()) {
                            case "male":
                                maleList.add(m);
                                break;
                            case "female":
                                femaleList.add(m);
                                break;
                            default:
                                unknownList.add(m);
                                break;
                        }
                    }
                    return null;
                });
            }

            // 🧵 Submit all parsing+validation tasks to thread pool and wait for results
            executor.invokeAll(tasks);
        } finally {
            executor.shutdown(); // Stop accepting new tasks
        }

        // ✅ Summary output of total records parsed and grouped
        System.out.println("🔍 Total records loaded: " +
                (maleList.size() + femaleList.size() + unknownList.size() + invalidList.size()));

        // 📦 Create a streaming Excel workbook (avoids memory explosion for large datasets)
        try (SXSSFWorkbook wb = new SXSSFWorkbook(100); // Keep 100 rows in memory, rest are flushed to disk
             FileOutputStream fos = new FileOutputStream("processed_members_2.xlsx")) {

            // 🧩 This method creates a sheet from a given member list and writes it into the workbook
            BiConsumer<String, List<Member>> writeSheet = (sheetName, memberList) -> {
                long t0 = System.nanoTime(); // For timing this specific sheet

                Sheet sheet = wb.createSheet(sheetName);
                String[] headers = {"ID Number", "Name", "Mobile Number", "Email Address", "Gender"};

                // Write the column headers
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < headers.length; i++) {
                    headerRow.createCell(i).setCellValue(headers[i]);
                }

                // Write the member records line-by-line
                int rowIndex = 1;
                for (Member m : memberList) {
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(m.id);
                    row.createCell(1).setCellValue(m.name);
                    row.createCell(2).setCellValue(m.mobile);
                    row.createCell(3).setCellValue(m.email);
                    row.createCell(4).setCellValue(m.gender);
                }

                // Time taken to create the sheet
                double seconds = (System.nanoTime() - t0) / 1_000_000_000.0;
                System.out.printf(" Sheet '%s' created with %d records in %.2f seconds%n",
                        sheetName, memberList.size(), seconds);
            };

            // 🧾 Create each sheet for valid gender categories and invalid data
            writeSheet.accept("Male", maleList);
            writeSheet.accept("Female", femaleList);
            writeSheet.accept("Unknown", unknownList);
            writeSheet.accept("Invalid", invalidList);

            // 💾 Write the Excel workbook to disk
            wb.write(fos);

            // 🧹 Clean up temporary files (important when using SXSSFWorkbook)
            wb.dispose();
        }

        // ⏱ End time after full processing
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toSeconds();

        // ✅ Final report of time taken
        System.out.println("🏁 Total processing completed in " + duration + " seconds");
    }
}
