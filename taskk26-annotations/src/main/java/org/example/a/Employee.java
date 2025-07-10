package org.example.a;

public class Employee {

    @Description
    private int identificationType;

    @Description(info = "Relationship to the member (e.g., MOTHER, SPOUSE)")
    private String relationship;

    @Description(info = "Date of birth of the nominee")
    private String dateOfBirth;

    private String address; // not annotated

    @Description(info = "Nominee's mobile phone number")
    private String mobileNo;

    @Description(info = "Nominee's email address")
    private String emailAddress;

    @Description(info = "Whether the person is marked as next of kin")
    private String isNextOfKin;

    @Description(info = "Whether this nominee is a beneficiary")
    private String isBeneficiary;

    private String isContactPerson; // not annotated

    @Description(info = "Indicates whether this nominee is officially recognized")
    private String isNominee;
}
