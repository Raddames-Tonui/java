package org.example.dto;

/**
 * DTO representing a summarized book report entry.
 */
public record BookReportDTO(
        Long catalogId,
        String bookName,
        String isbn,
        String bookLanguage,
        int publishedYear,
        String bookType,
        String subcategoryName
     ) {}
