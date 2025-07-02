package org.tonui.dao;

import org.tonui.db.DBConnection;
import org.tonui.model.Pupil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all database operations for pupils table.
 */
public class PupilDAO {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    /**
     * Inserts a new pupil into the database.
     */
    public boolean insertPupil(Pupil pupil) {
        String sql = "INSERT INTO pupils (pupil_firstname, pupil_lastname, pupil_email, gender, class_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pupil.getFirstName());
            stmt.setString(2, pupil.getLastName());
            stmt.setString(3, pupil.getEmail());
            stmt.setString(4, pupil.getGender());
            stmt.setLong(5, pupil.getClassId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting pupil: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all pupils from the database.
     */
    public List<Pupil> getAllPupils() {
        List<Pupil> pupils = new ArrayList<>();
        String sql = "SELECT * FROM pupils";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pupil pupil = new Pupil();
                pupil.setPupilId(rs.getLong("pupil_id"));
                pupil.setFirstName(rs.getString("pupil_firstname"));
                pupil.setLastName(rs.getString("pupil_lastname"));
                pupil.setEmail(rs.getString("pupil_email"));
                pupil.setGender(rs.getString("gender"));
                pupil.setClassId(rs.getLong("class_id"));
                pupils.add(pupil);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving pupils: " + e.getMessage());
        }

        return pupils;
    }

    /**
     * Retrieves a pupil by ID.
     */
    public Pupil getPupilById(Long pupilId) {
        String sql = "SELECT * FROM pupils WHERE pupil_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, pupilId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pupil p = new Pupil();
                    p.setPupilId(rs.getLong("pupil_id"));
                    p.setFirstName(rs.getString("pupil_firstname"));
                    p.setLastName(rs.getString("pupil_lastname"));
                    p.setEmail(rs.getString("pupil_email"));
                    p.setGender(rs.getString("gender"));
                    p.setClassId(rs.getLong("class_id"));
                    return p;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error getting pupil by ID: " + e.getMessage());
        }

        return null;
    }

    /**
     * Updates a pupil's record in the database.
     */
    public boolean updatePupil(Pupil pupil) {
        String sql = "UPDATE pupils SET pupil_firstname = ?, pupil_lastname = ?, pupil_email = ?, gender = ?, class_id = ?, date_modified = NOW() WHERE pupil_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pupil.getFirstName());
            stmt.setString(2, pupil.getLastName());
            stmt.setString(3, pupil.getEmail());
            stmt.setString(4, pupil.getGender());
            stmt.setLong(5, pupil.getClassId());
            stmt.setLong(6, pupil.getPupilId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating pupil: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a pupil by ID.
     */
    public boolean deletePupil(Long pupilID) {
        String sql = "DELETE FROM pupils WHERE pupil_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, pupilID);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting pupil: " + e.getMessage());
            return false;
        }
    }


    /**
     *  Filter pupils by class ID (Query Parameters)
     */
    public List<Pupil> getPupilsByClassId(Long classId){
        List<Pupil> pupils = new ArrayList<>();
        String sql = "SELECT * FROM pupils WHERE class_id = ?";

        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, classId);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    Pupil p = new Pupil();
                    p.setPupilId(rs.getLong("pupil_id"));
                    p.setFirstName(rs.getString("pupil_firstname"));
                    p.setLastName(rs.getString("pupil_lastname"));
                    p.setEmail(rs.getString("pupil_email"));
                    p.setGender(rs.getString("gender"));
                    p.setClassId(rs.getLong("class_id"));
                    pupils.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pupils;
    }

}
