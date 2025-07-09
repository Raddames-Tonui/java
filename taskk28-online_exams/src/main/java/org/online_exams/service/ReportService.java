package org.online_exams.service;

import org.online_exams.dto.AnsweredQuestionDTO;
import org.online_exams.dto.PupilExamRankingDTO;
import org.online_exams.dto.PupilExamReportDTO;
import org.online_exams.dto.TopPupilDTO;
import org.online_exams.repository.ReportRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class for computing exam results and generating reports.
 */
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(Connection conn) {
        this.reportRepository = new ReportRepository(conn);
    }

    // Pupil exam report
    public PupilExamReportDTO getPupilExamReport(Long pupilId, Long examId) throws SQLException {

        // Validate existence
        String pupilName = reportRepository.getPupilFullName(pupilId);
        if (pupilName == null){
            throw new IllegalArgumentException("Pupil not found");
        }

        String examTitle = reportRepository.getExamTitle(examId);
        if (examTitle == null){
            throw new IllegalArgumentException("Exam not found");
        }

        List<AnsweredQuestionDTO> answers = reportRepository.getPupilAnswersForExam(pupilId, examId);

        int totalMarks = 0;
        int marksScored = 0;

        for (AnsweredQuestionDTO answer : answers) {
            totalMarks += answer.questionMarks();
            if (answer.isCorrect()) {
                marksScored += answer.questionMarks();
            }
        }

        double percentage = totalMarks == 0 ? 0.0 : ((double) marksScored / totalMarks) * 100;

        // DTO to return pupil and exam report
        return new PupilExamReportDTO(
                examId,
                reportRepository.getExamTitle(examId),
                reportRepository.getPupilFullName(pupilId),
                answers,
                totalMarks,
                marksScored,
                percentage
        );
    }

    // Top 5 pupils
    public List<TopPupilDTO> getTop5Pupils(Long examId) throws SQLException {
        String exam = reportRepository.getExamTitle(examId);
        if (exam == null){
            throw new IllegalArgumentException("Exam not found");
        }
        return reportRepository.getTop5PupilsByExam(examId);
    }

    public List<PupilExamRankingDTO> getAllExamRankings() throws SQLException {
        return reportRepository.getAllExamScores();
    }

}
