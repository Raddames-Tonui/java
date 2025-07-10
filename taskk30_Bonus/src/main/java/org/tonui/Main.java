package org.tonui;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Main {

    static class Member {
        String id, name, mobile, email, gender;

        public Member(String id, String name, String mobile, String email, String gender) {
            this.id = id == null ? "" : id.trim();
            this.name = name == null ? "" : name.trim();
            this.mobile = mobile == null ? "" : mobile.trim().replaceAll("\\s+", "");
            this.email = email == null ? "" : email.trim().replaceAll("\\s+", "");
            this.gender = gender == null ? "" : gender.trim();
        }

        public boolean isValidID() { return id.matches("^\\d{6,12}$"); }

        public boolean isValidMobile() {
            return mobile.matches("^(07\\d{8}|2547\\d{8}|\\d{10,13})$");
        }

        public boolean isValidEmail() {
            return email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        }

        public boolean isValid() { return isValidID() && isValidMobile() && isValidEmail(); }
    }

    public static void main(String[] args) throws Exception {


        Path path = Paths.get(ClassLoader.getSystemResource("member_details.csv").toURI());
        List<String> lines = Files.readAllLines(path);
        lines.remove(0); // remove header

        Queue<Member> maleList = new ConcurrentLinkedQueue<>();
        Queue<Member> femaleList = new ConcurrentLinkedQueue<>();
        Queue<Member> unknownList = new ConcurrentLinkedQueue<>();
        Queue<Member> invalidList = new ConcurrentLinkedQueue<>();

        lines.parallelStream().forEach(line -> {
            String[] fields = line.split(",", -1);
            if (fields.length < 5) return;

            Member m = new Member(fields[0], fields[1], fields[2], fields[3], fields[4]);
            if (!m.isValid()) {
                invalidList.add(m);
            } else {
                switch (m.gender.toLowerCase()) {
                    case "male" -> maleList.add(m);
                    case "female" -> femaleList.add(m);
                    default -> unknownList.add(m);
                }
            }
        });


        System.out.println("🔍 Total records loaded: " +
                (maleList.size() + femaleList.size() + unknownList.size() + invalidList.size()));
        Instant start = Instant.now();
        try (SXSSFWorkbook wb = new SXSSFWorkbook(100);
             FileOutputStream fos = new FileOutputStream("processed_members.xlsx")) {

            BiConsumer<String, Collection<Member>> writeSheet = (sheetName, memberList) -> {
                long t0 = System.nanoTime();

                Sheet sheet = wb.createSheet(sheetName);
                String[] headers = {"ID Number", "Name", "Mobile Number", "Email Address", "Gender"};
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < headers.length; i++) {
                    headerRow.createCell(i).setCellValue(headers[i]);
                }

                int rowIndex = 1;
                for (Member m : memberList) {
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(m.id);
                    row.createCell(1).setCellValue(m.name);
                    row.createCell(2).setCellValue(m.mobile);
                    row.createCell(3).setCellValue(m.email);
                    row.createCell(4).setCellValue(m.gender);
                }

                double seconds = (System.nanoTime() - t0) / 1_000_000_000.0;
                System.out.printf(" Sheet '%s' created with %d records in %.2f seconds%n",
                        sheetName, memberList.size(), seconds);
            };

            // ✅ Write sheets sequentially for safety
            writeSheet.accept("Male", maleList);
            writeSheet.accept("Female", femaleList);
            writeSheet.accept("Unknown", unknownList);
            writeSheet.accept("Invalid", invalidList);

            wb.write(fos);
            wb.dispose();
        }

        Instant end = Instant.now();
        System.out.println("🏁 Total processing completed in " +
                Duration.between(start, end).toSeconds() + " seconds");
    }
}