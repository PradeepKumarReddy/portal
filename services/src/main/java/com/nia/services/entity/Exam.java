package com.nia.services.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="EXAM")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exam implements Comparable<Exam>{

	@Id
	@Column(name = "EXAM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "exam_description")
	private String examDescription;
	
	@Column(name = "exam_date")
	private Date examDate;
	
	@Column(name = "exam_name")
	private String examName;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@OneToMany(
			cascade = CascadeType.ALL, 
			mappedBy="exam"
    )
	@OrderBy("id asc")
	@JsonManagedReference
	private List<Question> questions = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	private Integer totalNoOfQuestions;
	
	@Transient
	private Integer noOfAnsweredQuestions;
	
	@Transient
	private Boolean attended;
	
	@Transient
	private Double marks;

	public Integer getTotalNoOfQuestions() {
		return totalNoOfQuestions;
	}

	public void setTotalNoOfQuestions(Integer totalNoOfQuestions) {
		this.totalNoOfQuestions = totalNoOfQuestions;
	}

	public Integer getNoOfAnsweredQuestions() {
		return noOfAnsweredQuestions;
	}

	public void setNoOfAnsweredQuestions(Integer noOfAnsweredQuestions) {
		this.noOfAnsweredQuestions = noOfAnsweredQuestions;
	}

	public Boolean getAttended() {
		return attended;
	}

	public void setAttended(Boolean attended) {
		this.attended = attended;
	}

	public Double getMarks() {
		return marks;
	}

	public void setMarks(Double marks) {
		this.marks = marks;
	}
	
	public Exam(Long id, String examDescription, Date examDate, String examName, boolean isActive,
			Integer totalNoOfQuestions, Integer noOfAnsweredQuestions, Double marks, String username) {
		super();
		this.id = id;
		this.examDescription = examDescription;
		this.examDate = examDate;
		this.examName = examName;
		this.isActive = isActive;
		this.totalNoOfQuestions = totalNoOfQuestions;
		this.noOfAnsweredQuestions = noOfAnsweredQuestions;
		this.marks = marks;
		if(username == null || (username != null && username.length() == 0)) {
			setAttended(false);
		} else {
			setAttended(true);
		}
	}


	public Exam() {
		super();
	}

	@Override
	public int compareTo(Exam o) {
		return id.compareTo(o.getId());
	}

	
}
