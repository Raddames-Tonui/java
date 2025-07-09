package org.online_exams.service;

import org.online_exams.dto.ExamResponseDTO;
import org.online_exams.repository.ExamRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(Connection conn) {
        this.examRepository = new ExamRepository(conn);
    }

    public List<ExamResponseDTO> getExamsByTeacherId(Long teacherId) throws SQLException {
        if (teacherId == null) {
            throw new IllegalArgumentException("Teacher ID cannot be null");
        }

        boolean teacherExists = examRepository.teacherExists(teacherId);
        if (!teacherExists) {
            throw new IllegalArgumentException("Teacher not found with ID: " + teacherId);
        }

        return examRepository.findExamsByTeacherId(teacherId);
    }
}
