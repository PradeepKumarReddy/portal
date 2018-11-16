package com.nia.services.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="SUBJECT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subject {

	@Id
	@Column(name = "SUBJECT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(
			cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER,
			orphanRemoval = true,
			mappedBy="subject"
    )
	@OrderBy("orderId ASC")
	@JsonManagedReference
	private Set<SubResource> subResource = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SubResource> getSubResource() {
		return subResource;
	}

	public void setSubResource(Set<SubResource> subResource) {
		this.subResource = subResource;
	}
	
}
