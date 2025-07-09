package org.online_exams.model;

import java.sql.Timestamp;

/**
 * Domain model for a pupil entity.
 */
public class Pupil {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private long classId;
    private Timestamp dateCreated;
    private Timestamp dateModified;

    public Pupil() {} // for Jackson

    public Pupil(long id, String firstname, String lastname, String email, String gender, long classId,
                 Timestamp dateCreated, Timestamp dateModified) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.classId = classId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    // Getters and setters
    public long getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public long getClassId() { return classId; }
    public Timestamp getDateCreated() { return dateCreated; }
    public Timestamp getDateModified() { return dateModified; }

    public void setId(long id) { this.id = id; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setEmail(String email) { this.email = email; }
    public void setGender(String gender) { this.gender = gender; }
    public void setClassId(long classId) { this.classId = classId; }
    public void setDateCreated(Timestamp dateCreated) { this.dateCreated = dateCreated; }
    public void setDateModified(Timestamp dateModified) { this.dateModified = dateModified; }

    @Override
    public String toString() {
        return super.toString();
    }
}
