package com.nia.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BackupUserRegister {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long backupId;
	
	@Column(name = "REGISTRATION_ID")
	private String registrationId;

	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "FATHER_NAME")
	private String fatherName;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "DOB")
	private String dob;
	
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;
	
	@Column(name = "RESIDENTIAL_ADDR")
	private String residentialAddress;
	
	@Column(name = "RESIDENTIAL_PINCODE")
	private String residentialPincode;
	
	@Column(name = "RESIDENTIAL_STATE")
	private String residentialState;
	
	@Column(name = "PERMANENT_ADDR")
	private String permanentAddress;
	
	@Column(name = "PERMANENT_STATE")
	private String permanentState;
	
	@Column(name = "PERMANENT_PINCODE")
	private String permanentPincode;
	
	@Column (name = "PHONE")
	private String phoneNumber;
	
	@Column (name = "GROUP1")
	private boolean group1;
	
	@Column (name = "GROUP2")
	private boolean group2;
	
	@Column (name = "GROUP3")
	private boolean group3;


	public Long getBackupId() {
		return backupId;
	}

	public void setBackupId(Long backupId) {
		this.backupId = backupId;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getResidentialPincode() {
		return residentialPincode;
	}

	public void setResidentialPincode(String residentialPincode) {
		this.residentialPincode = residentialPincode;
	}

	public String getResidentialState() {
		return residentialState;
	}

	public void setResidentialState(String residentialState) {
		this.residentialState = residentialState;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(String permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	/*
	 * @OneToOne(cascade= CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "USER_ID") private ApplicationUser applicationUser;
	 */

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isGroup1() {
		return group1;
	}

	public void setGroup1(boolean group1) {
		this.group1 = group1;
	}

	public boolean isGroup2() {
		return group2;
	}

	public void setGroup2(boolean group2) {
		this.group2 = group2;
	}

	public boolean isGroup3() {
		return group3;
	}

	public void setGroup3(boolean group3) {
		this.group3 = group3;
	}

	@Override
	public String toString() {
		return "BackupUserRegister [id=" + backupId + ", registrationId=" + registrationId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", fatherName=" + fatherName + ", gender=" + gender + ", dob=" + dob
				+ ", email=" + email + ", residentialAddress=" + residentialAddress + ", residentialPincode="
				+ residentialPincode + ", residentialState=" + residentialState + ", permanentAddress="
				+ permanentAddress + ", permanentState=" + permanentState + ", permanentPincode=" + permanentPincode
				+ ", phoneNumber=" + phoneNumber + ", group1=" + group1 + ", group2=" + group2 + ", group3=" + group3
				+ "]";
	}

	
}
