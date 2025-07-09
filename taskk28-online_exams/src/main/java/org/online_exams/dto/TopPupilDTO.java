
package org.online_exams.dto;

public record TopPupilDTO(
        Long pupilId,
        String pupilName,
        int totalMarks,
        int marksScored,
        double percentage
) {}
