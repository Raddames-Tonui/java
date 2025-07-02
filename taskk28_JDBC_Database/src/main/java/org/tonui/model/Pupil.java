package org.tonui.model;

public class Pupil {
    private long pupilId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private long classId;


//    Constructors
    public Pupil(){}

    public Pupil(long pupilId, String firstName, String lastName, String email, String gender, long classId) {
        this.pupilId = pupilId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.classId = classId;

    }

//    Getters and setters

    public long getPupilId() {
        return pupilId;
    }

    public void setPupilId(long pupilId) {
        this.pupilId = pupilId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Pupil {" +
                "id=" + pupilId +
                ", name='" + firstName + " " + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", classId=" + classId +
                '}';
    }


}
