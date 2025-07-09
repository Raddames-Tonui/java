package org.online_exams.repository;

import org.online_exams.dto.AnsweredQuestionDTO;
import org.online_exams.dto.PupilExamRankingDTO;
import org.online_exams.dto.TopPupilDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository {
    private final Connection conn;

    public ReportRepository(Connection conn) {
        this.conn = conn;
    }

    public List<AnsweredQuestionDTO> getPupilAnswersForExam(Long pupilId, Long examId) throws SQLException {
        String sql = """
                SELECT 
                    q.question_id,
                    q.question_text,
                    q.question_marks,
                    c.choice_label,
                    c.choice_is_correct
                FROM submissions s
                JOIN answers a ON s.submission_id = a.submission_id
                JOIN questions q ON a.question_id = q.question_id
                JOIN choices c ON a.choice_id = c.choice_id
                WHERE s.exam_id = ? AND s.pupil_id = ?
                """;

        try(PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setLong(1, examId);
            stmt.setLong(2, pupilId);

            ResultSet rs = stmt.executeQuery();
            List<AnsweredQuestionDTO> answers = new ArrayList<>();

            while (rs.next()) {
                answers.add(new AnsweredQuestionDTO(
                        rs.getLong("question_id"),
                        rs.getString("question_text"),
                        rs.getString("choice_label"),
                        rs.getBoolean("choice_is_correct"),
                        rs.getInt("question_marks")
                ));
            }
            return answers;
        }

    }

    public String getExamTitle(Long examId) throws SQLException {
        String query = "SELECT exam_title FROM exams WHERE exam_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setLong(1, examId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("exam_title") : null;
        }
    }

    public String getPupilFullName(Long pupilId) throws SQLException {
        String query = "SELECT pupil_firstname, pupil_lastname FROM pupils WHERE pupil_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, pupilId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("pupil_firstname") + " " + rs.getString("pupil_lastname");
            }
            return null;
        }
    }

    // Top pupils order
    public List<TopPupilDTO> getTop5PupilsByExam(Long examId) throws SQLException {
        String sql = """
                  SELECT
                            p.pupil_id,
                            CONCAT(p.pupil_firstname, ' ', p.pupil_lastname) AS full_name,
                            SUM(q.question_marks) AS total_marks,
                            SUM(
                                CASE WHEN c.choice_is_correct THEN q.question_marks ELSE 0 END
                            ) AS marks_scored
                        FROM submissions s
                        JOIN pupils p ON p.pupil_id = s.pupil_id
                        JOIN answers a ON a.submission_id = s.submission_id
                        JOIN choices c ON c.choice_id = a.choice_id
                        JOIN questions q ON q.question_id = a.question_id
                        WHERE s.exam_id = ?
                        GROUP BY p.pupil_id, full_name
                        ORDER BY marks_scored DESC
                        LIMIT 5;
                """;

        List<TopPupilDTO> topPupils = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setLong(1, examId);

            try(ResultSet rs = stmt.executeQuery();){
                while (rs.next()){
                    int total = rs.getInt("total_marks");
                    int scored = rs.getInt("marks_scored");
                    double percentage = total == 0 ? 0.0 : ((double) scored / total) * 100;

                    topPupils.add(new TopPupilDTO(
                       rs.getLong("pupil_id"),
                       rs.getString("full_name"),
                       total,
                       scored,
                       percentage
                    ));
                }
            }
        return topPupils;
        }
    }

    public List<PupilExamRankingDTO> getAllExamScores() throws SQLException {
        String sql = """
                 SELECT
                        s.exam_id,
                        e.exam_title,
                        p.pupil_id,
                        CONCAT(p.pupil_firstname, ' ', p.pupil_lastname) AS full_name,
                        SUM(q.question_marks) AS total_marks,
                        SUM(CASE WHEN c.choice_is_correct THEN q.question_marks ELSE 0 END) AS marks_scored
                    FROM submissions s
                    JOIN pupils p ON p.pupil_id = s.pupil_id
                    JOIN exams e ON e.exam_id = s.exam_id
                    JOIN answers a ON a.submission_id = s.submission_id
                    JOIN questions q ON q.question_id = a.question_id
                    JOIN choices c ON c.choice_id = a.choice_id
                    GROUP BY s.exam_id, e.exam_title, p.pupil_id, full_name
                    ORDER BY s.exam_id, marks_scored DESC
                """;

        List <PupilExamRankingDTO> pupils = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            Long currentExamId = null;
            int rank = 0;

            while(rs.next()){
                Long examId = rs.getLong("exam_id");

                if(!currentExamId.equals(examId)){
                    currentExamId = examId;
                    rank = 1;
                }else {
                    rank ++;
                }

                int total = rs.getInt("total_marks");
                int scored = rs.getInt("marks_scored");
                double percentage = total == 0 ? 0.0 : ((double) scored / total) * 100;

                pupils.add(new PupilExamRankingDTO(
                        examId,
                        rs.getString("exam_title"),
                        rs.getLong("pupil_id"),
                        rs.getString("full_name"),
                        total,
                        scored,
                        percentage,
                        rank
                ));
            }
        };
        return pupils;

    }
}
