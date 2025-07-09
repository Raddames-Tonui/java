package org.online_exams.dto;

import java.util.List;

/**
 * Record representing a full report of a pupil's exam.
 */
public record PupilExamReportDTO(
        Long examId,
        String examTitle,
        String pupilName,
        List<AnsweredQuestionDTO> questions,
        int totalMarks,
        int marksScored,
        double percentage
) {}
