package org.tonui.dao;

import org.tonui.db.DBConnection;
import org.tonui.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    public void insertSubject(Subject subject) {
        String sql = "INSERT INTO subjects (subject_code, subject_name) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getSubjectCode());
            stmt.setString(2, subject.getSubjectName());
            stmt.executeUpdate();

            System.out.println("Subject inserted successfully.");

        } catch (SQLException e) {
            System.err.println("Error inserting subject: " + e.getMessage());
        }
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subjects";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectCode(rs.getString("subject_code"));
                subject.setSubjectName(rs.getString("subject_name"));

                subjects.add(subject);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching subjects: " + e.getMessage());
        }

        return subjects;
    }

    public void updateSubjectName(int subjectId, String newName) {
        String sql = "UPDATE subjects SET subject_name = ? WHERE subject_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, subjectId);
            int rows = stmt.executeUpdate();

            System.out.println("Updated " + rows + " subject(s).");

        } catch (SQLException e) {
            System.err.println("Error updating subject: " + e.getMessage());
        }
    }

    public void deleteSubject(int subjectId) {
        String sql = "DELETE FROM subjects WHERE subject_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, subjectId);
            int rows = stmt.executeUpdate();

            System.out.println("🗑️ Deleted " + rows + " subject(s).");

        } catch (SQLException e) {
            System.err.println("Error deleting subject: " + e.getMessage());
        }
    }
}
