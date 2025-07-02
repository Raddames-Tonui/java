package org.tonui.service;

import org.tonui.dao.PupilDAO;
import org.tonui.model.Pupil;

import java.util.List;

/**
 * Service layer for handling business logic related to pupils.
 */
public class PupilService {
    private final PupilDAO pupilDAO = new PupilDAO();

    /**
     * Creates a new pupil after validating input.
     */
    public boolean createPupil(Pupil pupil) {
        if (pupil.getEmail() == null || pupil.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        return pupilDAO.insertPupil(pupil);
    }

    /**
     * Retrieves all pupils from the database.
     */
    public List<Pupil> getAll() {
        return pupilDAO.getAllPupils();
    }

    /**
     * Retrieves a pupil by their unique ID.
     */
    public Pupil getById(Long id) {
        return pupilDAO.getPupilById(id);
    }

    /**
     * Updates a pupil’s record.
     */
    public boolean update(Pupil pupil) {
        return pupilDAO.updatePupil(pupil);
    }

    /**
     * Deletes a pupil by ID.
     */
    public boolean delete(Long id) {
        return pupilDAO.deletePupil(id);
    }

    /**
     * Returns all pupils associated with a given class ID.
     */
    public List<Pupil> getByClassId(Long classId) {
        return pupilDAO.getPupilsByClassId(classId);
    }
}
