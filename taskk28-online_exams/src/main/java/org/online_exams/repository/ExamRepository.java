package org.online_exams.repository;

import org.online_exams.dto.ExamResponseDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamRepository {
    private final Connection conn;

    public ExamRepository(Connection conn) {
        this.conn = conn;
    }

    public List<ExamResponseDTO> findExamsByTeacherId(Long teacherId) {
        String sql = """
            SELECT 
                e.exam_id,
                e.exam_title,
                s.subject_name,
                t.teacher_firstname,
                t.teacher_lastname,
                e.date_created
            FROM exams e
            JOIN subjects s ON e.subject_id = s.subject_id
            JOIN teachers t ON e.teacher_id = t.teacher_id
            WHERE e.teacher_id = ?
            ORDER BY e.date_created DESC
        """;

        List<ExamResponseDTO> exams = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ExamResponseDTO exam = new ExamResponseDTO();
                    exam.setExamId(rs.getLong("exam_id"));
                    exam.setExamTitle(rs.getString("exam_title"));
                    exam.setSubjectName(rs.getString("subject_name"));
                    exam.setTeacherFirstName(rs.getString("teacher_firstname"));
                    exam.setTeacherLastName(rs.getString("teacher_lastname"));
                    exams.add(exam);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching exams by teacher ID", e);
        }

        return exams;
    }

    public boolean teacherExists(Long teacherId) throws SQLException {
        String sql = "SELECT 1 FROM teachers WHERE teacher_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
