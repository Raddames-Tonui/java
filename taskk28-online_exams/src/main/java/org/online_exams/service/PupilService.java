package org.online_exams.service;

import org.online_exams.model.Pupil;
import org.online_exams.repository.PupilRepository;
import org.online_exams.dto.pupil.PupilDTO;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles business logic for managing pupils.
 * Acts as an intermediary between the controller and repository layer.
 */
public class PupilService {
    private final PupilRepository pupilRepository;

    public PupilService(Connection conn) {
        this.pupilRepository = new PupilRepository(conn);
    }

    /**
     * Returns all pupils converted to DTOs.
     */
    public List<PupilDTO> getAllPupils() {
        List<Pupil> pupils = pupilRepository.findAll();
        return pupils.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Returns a pupil by ID as a DTO, or null if not found.
     */
    public PupilDTO getPupilById(long id) {
        Pupil pupil = pupilRepository.findById(id);
        return pupil != null ? toDTO(pupil) : null;
    }

    /**
     * Creates a new pupil and returns the created data as DTO.
     */
    public PupilDTO createPupil(Pupil pupil) {
        if (pupilRepository.emailExists(pupil.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        Pupil created = pupilRepository.create(pupil);
        return toDTO(created);
    }

    /**
     * Updates a pupil by ID.
     * Returns true if successful.
     */
    public boolean updatePupil(long id, Pupil updated) {
        return pupilRepository.update(id, updated);
    }

    /**
     * Deletes a pupil by ID.
     * Returns true if deleted successfully.
     */
    public boolean deletePupil(long id) {
        return pupilRepository.delete(id);
    }

    /**
     * Converts a Pupil model to a DTO.
     */
    private PupilDTO toDTO(Pupil pupil) {
        return new PupilDTO(
                pupil.getId(),
                    pupil.getFirstname(),
                pupil.getLastname(),
                pupil.getEmail(),
                pupil.getGender(),
                pupil.getClassId()
        );
    }
}
