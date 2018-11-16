package com.nia.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="SHOW_REGISTER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShowRegister {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="VIEW_REGISTER_PAGE")
	private boolean viewRegisterPage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isViewRegisterPage() {
		return viewRegisterPage;
	}

	public void setViewRegisterPage(boolean viewRegisterPage) {
		this.viewRegisterPage = viewRegisterPage;
	}
	
	
}
