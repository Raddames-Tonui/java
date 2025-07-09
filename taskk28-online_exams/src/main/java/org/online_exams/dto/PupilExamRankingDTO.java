package org.online_exams.dto;

public record PupilExamRankingDTO(
        Long examId,
        String examTitle,
        Long pupilId,
        String pupilName,
        int totalMarks,
        int marksScored,
        double percentage,
        int rank
) {}
