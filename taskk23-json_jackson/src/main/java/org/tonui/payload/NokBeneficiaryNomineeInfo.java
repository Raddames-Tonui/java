package org.tonui.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NokBeneficiaryNomineeInfo {
    @JsonProperty("identification_type")
    private int identificationType;

    private String relationship;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    private String address;

    @JsonProperty("mobile_no")
    private String mobileNo;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("is_next_of_kin")
    private String isNextOfKin;

    @JsonProperty("is_beneficiary")
    private String isBeneficiary;

    @JsonProperty("is_contact_person")
    private String isContactPerson;

    @JsonProperty("is_nominee")
    private String isNominee;

    private int allocation;

    // Getters and setters
    public int getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(int identificationType) {
        this.identificationType = identificationType;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getIsNextOfKin() {
        return isNextOfKin;
    }

    public void setIsNextOfKin(String isNextOfKin) {
        this.isNextOfKin = isNextOfKin;
    }

    public String getIsBeneficiary() {
        return isBeneficiary;
    }

    public void setIsBeneficiary(String isBeneficiary) {
        this.isBeneficiary = isBeneficiary;
    }

    public String getIsContactPerson() {
        return isContactPerson;
    }

    public void setIsContactPerson(String isContactPerson) {
        this.isContactPerson = isContactPerson;
    }

    public String getIsNominee() {
        return isNominee;
    }

    public void setIsNominee(String isNominee) {
        this.isNominee = isNominee;
    }

    public int getAllocation() {
        return allocation;
    }

    public void setAllocation(int allocation) {
        this.allocation = allocation;
    }


    @Override
    public String toString() {
        return "NokBeneficiaryNomineeInfo{" +
                "identificationType=" + identificationType +
                ", relationship='" + relationship + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", isNextOfKin='" + isNextOfKin + '\'' +
                ", isBeneficiary='" + isBeneficiary + '\'' +
                ", isContactPerson='" + isContactPerson + '\'' +
                ", isNominee='" + isNominee + '\'' +
                ", allocation=" + allocation +
                '}';
    }
}
