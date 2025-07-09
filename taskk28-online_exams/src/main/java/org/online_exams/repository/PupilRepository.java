package org.online_exams.repository;

import org.online_exams.model.Pupil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles raw database interactions for the pupil table.
 */
public class PupilRepository {
    private final Connection conn;

    public PupilRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Pupil> findAll() {
        List<Pupil> pupils = new ArrayList<>();
        String sql = "SELECT * FROM pupils";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pupils.add(mapRowToPupil(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all pupils", e);
        }
        return pupils;
    }

    public Pupil findById(long id) {
        String sql = "SELECT * FROM pupils WHERE pupil_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? mapRowToPupil(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching pupil by ID", e);
        }
    }

    public Pupil create(Pupil pupil) {
        String sql = """
            INSERT INTO pupils (pupil_firstname, pupil_lastname, pupil_email, gender, class_id)
            VALUES (?, ?, ?, ?, ?)
            RETURNING *
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pupil.getFirstname());
            stmt.setString(2, pupil.getLastname());
            stmt.setString(3, pupil.getEmail());
            stmt.setString(4, pupil.getGender());
            stmt.setLong(5, pupil.getClassId());

            ResultSet rs = stmt.executeQuery();
            return rs.next() ? mapRowToPupil(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating pupil", e);
        }
    }

    public boolean update(long id, Pupil updated) {
        String sql = """
            UPDATE pupils SET
                pupil_firstname = ?,
                pupil_lastname = ?,
                pupil_email = ?,
                gender = ?,
                class_id = ?,
                date_modified = NOW()
            WHERE pupil_id = ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, updated.getFirstname());
            stmt.setString(2, updated.getLastname());
            stmt.setString(3, updated.getEmail());
            stmt.setString(4, updated.getGender());
            stmt.setLong(5, updated.getClassId());
            stmt.setLong(6, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating pupil", e);
        }
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM pupils WHERE pupil_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting pupil", e);
        }
    }

    public boolean emailExists(String email){
        String sql = "SELECT 1 FROM pupils WHERE pupil_email = ?";
        try(PreparedStatement stmt =conn.prepareStatement(sql)){
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        }catch (SQLException e){
            throw new RuntimeException("Error checking email exists", e);
        }
    }

    private Pupil mapRowToPupil(ResultSet rs) throws SQLException {
        return new Pupil(
                rs.getLong("pupil_id"),
                rs.getString("pupil_firstname"),
                rs.getString("pupil_lastname"),
                rs.getString("pupil_email"),
                rs.getString("gender"),
                rs.getLong("class_id"),
                rs.getTimestamp("date_created"),
                rs.getTimestamp("date_modified")
        );
    }
}
