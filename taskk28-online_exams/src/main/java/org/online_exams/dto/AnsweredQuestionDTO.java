package org.online_exams.dto;

/**
 * Record representing a question answered by a pupil.
 */
public record AnsweredQuestionDTO(
        Long questionId,
        String questionText,
        String selectedLabel,
        boolean isCorrect,
        int questionMarks
) {}
