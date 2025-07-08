package org.example.repository;

import org.example.dto.BookReportDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for fetching book reports based on category or subcategory
 */
public class BookReportRepository {
    private final Connection conn;

    public BookReportRepository(Connection conn) {
        this.conn = conn;
    }

    public List<BookReportDTO> findBooksByCategory(Long categoryId) throws SQLException {
        String query = """
            SELECT c.catalog_id, c.book_name, c.isbn, c.book_language, 
                   c.published_year, c.book_type, sc.subcategory_name,  sc.subcategory_description
            FROM catalog c
            JOIN rack r ON c.rack_id = r.rack_id
            JOIN subcategory sc ON r.subcategory_id = sc.subcategory_id
            JOIN category cat ON sc.category_id = cat.category_id
            WHERE cat.category_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            List<BookReportDTO> books = new ArrayList<>();
            while (rs.next()) {
                books.add(new BookReportDTO(
                        rs.getLong("catalog_id"),
                        rs.getString("book_name"),
                        rs.getString("isbn"),
                        rs.getString("book_language"),
                        rs.getInt("published_year"),
                        rs.getString("book_type"),
                        rs.getString("subcategory_name"),
                        rs.getString("subcategory_description")
                ));
            }
            return books;
        }
    }

    public List<BookReportDTO> findBooksBySubcategory(Long subcategoryId) throws SQLException {
        String query = """
            SELECT c.catalog_id, c.book_name, c.isbn, c.book_language, 
                   c.published_year, c.book_type, sc.subcategory_name,  sc.subcategory_description
            FROM catalog c
            JOIN rack r ON c.rack_id = r.rack_id
            JOIN subcategory sc ON r.subcategory_id = sc.subcategory_id
            WHERE sc.subcategory_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, subcategoryId);
            ResultSet rs = stmt.executeQuery();
            List<BookReportDTO> books = new ArrayList<>();
            while (rs.next()) {
                books.add(new BookReportDTO(
                        rs.getLong("catalog_id"),
                        rs.getString("book_name"),
                        rs.getString("isbn"),
                        rs.getString("book_language"),
                        rs.getInt("published_year"),
                        rs.getString("book_type"),
                        rs.getString("subcategory_name"),
                        rs.getString("subcategory_description")));
            }
            return books;
        }
    }

    public boolean categoryExists(Long id) {
        return recordExists("SELECT COUNT(*) FROM category WHERE category_id = ?", id);
    }

    public boolean subcategoryExists(Long id) {
        return recordExists("SELECT COUNT(*) FROM subcategory WHERE subcategory_id = ?", id);
    }

    private boolean recordExists(String query, Long id) {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
