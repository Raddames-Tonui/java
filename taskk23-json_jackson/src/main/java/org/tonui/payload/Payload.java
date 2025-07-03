package org.tonui.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Payload {


//    @JsonProperty is used for renaming from the JSON snake case to Java Camel case in this context
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("other_names")
    private String otherNames;

    @JsonProperty("current_address")
    private String currentAddress;

    @JsonProperty("permanent_address")
    private String permanentAddress;

    @JsonProperty("phone_no")
    private String phoneNo;

    @JsonProperty("transactional_mobile_no")
    private String transactionalMobileNo;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("email_address")
    private String emailAddress;

    private String nationality;

    @JsonProperty("primary_identification_type")
    private int primaryIdentificationType;

    @JsonProperty("primary_identification")
    private String primaryIdentification;

    @JsonProperty("secondary_identification_type")
    private int secondaryIdentificationType;

    @JsonProperty("secondary_identification")
    private String secondaryIdentification;

    private String religion;

    @JsonProperty("office_telephone_no")
    private String officeTelephoneNo;

    @JsonProperty("marital_status")
    private int maritalStatus;

    private int gender;

    @JsonProperty("terms_of_employment")
    private int termsOfEmployment;

    @JsonProperty("post_code")
    private String postCode;

    private String city;

    private String county;

    @JsonProperty("sub_county")
    private String subCounty;

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("bank_branch_code")
    private String bankBranchCode;

    @JsonProperty("bank_account_no")
    private String bankAccountNo;

    @JsonProperty("kra_pin_number")
    private String kraPinNumber;

    @JsonProperty("recruited_by_type")
    private int recruitedByType;

    @JsonProperty("recruited_by")
    private String recruitedBy;

    @JsonProperty("is_self_employed")
    private String isSelfEmployed;

    private String salutation;

    @JsonProperty("date_of_contribution_commencement")
    private String dateOfContributionCommencement;

    @JsonProperty("contribution_amount")
    private double contributionAmount;

    @JsonProperty("nok_beneficiary_nominee_info")
    private List<NokBeneficiaryNomineeInfo> nokBeneficiaryNomineeInfo;

    private List<Account> accounts;

    // ====================== Getters and Setters ======================

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTransactionalMobileNo() {
        return transactionalMobileNo;
    }

    public void setTransactionalMobileNo(String transactionalMobileNo) {
        this.transactionalMobileNo = transactionalMobileNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getPrimaryIdentificationType() {
        return primaryIdentificationType;
    }

    public void setPrimaryIdentificationType(int primaryIdentificationType) {
        this.primaryIdentificationType = primaryIdentificationType;
    }

    public String getPrimaryIdentification() {
        return primaryIdentification;
    }

    public void setPrimaryIdentification(String primaryIdentification) {
        this.primaryIdentification = primaryIdentification;
    }

    public int getSecondaryIdentificationType() {
        return secondaryIdentificationType;
    }

    public void setSecondaryIdentificationType(int secondaryIdentificationType) {
        this.secondaryIdentificationType = secondaryIdentificationType;
    }

    public String getSecondaryIdentification() {
        return secondaryIdentification;
    }

    public void setSecondaryIdentification(String secondaryIdentification) {
        this.secondaryIdentification = secondaryIdentification;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getOfficeTelephoneNo() {
        return officeTelephoneNo;
    }

    public void setOfficeTelephoneNo(String officeTelephoneNo) {
        this.officeTelephoneNo = officeTelephoneNo;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getTermsOfEmployment() {
        return termsOfEmployment;
    }

    public void setTermsOfEmployment(int termsOfEmployment) {
        this.termsOfEmployment = termsOfEmployment;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getKraPinNumber() {
        return kraPinNumber;
    }

    public void setKraPinNumber(String kraPinNumber) {
        this.kraPinNumber = kraPinNumber;
    }

    public int getRecruitedByType() {
        return recruitedByType;
    }

    public void setRecruitedByType(int recruitedByType) {
        this.recruitedByType = recruitedByType;
    }

    public String getRecruitedBy() {
        return recruitedBy;
    }

    public void setRecruitedBy(String recruitedBy) {
        this.recruitedBy = recruitedBy;
    }

    public String getIsSelfEmployed() {
        return isSelfEmployed;
    }

    public void setIsSelfEmployed(String isSelfEmployed) {
        this.isSelfEmployed = isSelfEmployed;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getDateOfContributionCommencement() {
        return dateOfContributionCommencement;
    }

    public void setDateOfContributionCommencement(String dateOfContributionCommencement) {
        this.dateOfContributionCommencement = dateOfContributionCommencement;
    }

    public double getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public List<NokBeneficiaryNomineeInfo> getNokBeneficiaryNomineeInfo() {
        return nokBeneficiaryNomineeInfo;
    }

    public void setNokBeneficiaryNomineeInfo(List<NokBeneficiaryNomineeInfo> nokBeneficiaryNomineeInfo) {
        this.nokBeneficiaryNomineeInfo = nokBeneficiaryNomineeInfo;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    @Override
    public String toString() {
        return "Payload{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", otherNames='" + otherNames + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", transactionalMobileNo='" + transactionalMobileNo + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", nationality='" + nationality + '\'' +
                ", primaryIdentificationType=" + primaryIdentificationType +
                ", primaryIdentification='" + primaryIdentification + '\'' +
                ", secondaryIdentificationType=" + secondaryIdentificationType +
                ", secondaryIdentification='" + secondaryIdentification + '\'' +
                ", religion='" + religion + '\'' +
                ", officeTelephoneNo='" + officeTelephoneNo + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", gender=" + gender +
                ", termsOfEmployment=" + termsOfEmployment +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", subCounty='" + subCounty + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", bankBranchCode='" + bankBranchCode + '\'' +
                ", bankAccountNo='" + bankAccountNo + '\'' +
                ", kraPinNumber='" + kraPinNumber + '\'' +
                ", recruitedByType=" + recruitedByType +
                ", recruitedBy='" + recruitedBy + '\'' +
                ", isSelfEmployed='" + isSelfEmployed + '\'' +
                ", salutation='" + salutation + '\'' +
                ", dateOfContributionCommencement='" + dateOfContributionCommencement + '\'' +
                ", contributionAmount=" + contributionAmount +
                ", nokBeneficiaryNomineeInfo=" + nokBeneficiaryNomineeInfo +
                ", accounts=" + accounts +
                '}';
    }
}
