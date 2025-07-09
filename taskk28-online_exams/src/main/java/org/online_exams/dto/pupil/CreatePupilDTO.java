package org.online_exams.dto.pupil;

/**
 * DTO for creating a new pupil.
 * Used in POST requests to /api/pupils.
 */
public record CreatePupilDTO(
        String firstName,
        String lastName,
        String email,
        String gender,
        long classId
){}
