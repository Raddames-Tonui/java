package org.example.service;

import org.example.dto.BookReportDTO;
import org.example.repository.BookReportRepository;
import org.example.util.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Service layer to abstract report generation logic
 */
public class BookReportService {
    private final BookReportRepository bookReportRepo;

    public BookReportService(Connection conn) {
        this.bookReportRepo = new BookReportRepository(conn);
    }

    public List<BookReportDTO> getBooksByCategory(Long categoryId) throws SQLException {
        if (!bookReportRepo.categoryExists(categoryId)) {
            throw new NotFoundException("Category not found");
        }
        return bookReportRepo.findBooksByCategory(categoryId);
    }

    public List<BookReportDTO> getBooksBySubcategory(Long subcategoryId) throws SQLException {
        if (!bookReportRepo.subcategoryExists(subcategoryId)) {
            throw new NotFoundException("Subcategory not found");
        }
        return bookReportRepo.findBooksBySubcategory(subcategoryId);
    }

    // 1.3 Books by author
    public List<BookReportDTO> getBooksByAuthor(Long authorId) throws SQLException {
        if (!bookReportRepo.authorExists(authorId)) {
            throw new NotFoundException("Author not found");
        }
        return bookReportRepo.findBooksByAuthor(authorId);
    }

    // 1.4 Books add by librarian
    public List<BookReportDTO> getBooksByLibrarian(Long librarianId) throws SQLException {
        if (!bookReportRepo.librarianExists(librarianId)) {
            throw new NotFoundException("Librarian not found");
        }
        return bookReportRepo.findBooksByLibrarian(librarianId);
    }


}
