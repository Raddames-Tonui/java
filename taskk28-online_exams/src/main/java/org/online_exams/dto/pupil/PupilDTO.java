package org.online_exams.dto.pupil;

/**
 * A RECORD class in Java is a special kind of class
 * designed to make immutable data structures easy to write.
 * DTO returned to API clients when fetching pupil data.
 * Immutable, structured, and hides sensitive DB internals.
 */
public record PupilDTO(
        long id,
        String firstName,
        String lastName,
        String email,
        String gender,
        long classId
) {}
