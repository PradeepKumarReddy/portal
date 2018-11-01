package com.nia.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="CONTACTUS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContactUs {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="email1")
	private String email1;
	
	@Column(name="email2")
	private String email2;
	
	@Column(name="phone1")
	private String phone1;
	
	@Column(name="phone2")
	private String phone2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	
	
	

}
