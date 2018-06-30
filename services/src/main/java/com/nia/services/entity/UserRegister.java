package com.nia.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserRegister {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@Column(name = "EMAIL")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "UserRegister [id=" + id + ", registrationId=" + registrationId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", fatherName=" + fatherName + ", gender=" + gender + ", dob=" + dob
				+ ", email=" + email + ", residentialAddress=" + residentialAddress + ", residentialPincode="
				+ residentialPincode + ", residentialState=" + residentialState + ", permanentAddress="
				+ permanentAddress + ", permanentState=" + permanentState + ", permanentPincode=" + permanentPincode
				+ "]";
	}
	
	
}
