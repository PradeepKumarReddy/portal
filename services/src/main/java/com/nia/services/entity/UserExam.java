package com.nia.services.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="USER_EXAM")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserExam {
	
	@Id
	@Column(name = "USER_EXAM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(
			cascade = CascadeType.ALL, 
			orphanRemoval = true,
			mappedBy="userexam"
    )
	@JsonManagedReference
	private List<UserResponse> userResponses = new ArrayList<>();
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name = "EXAM_ID")
	private Long examId;
	
	@Column(name="TOTAL_QUESTIONS")
	private int totalNoOfQuestions;
	
	@Column(name="ANSWERED_QUESTIONS")
	private int noOfAnsweredQuestions;
	
	@Column(name="TOTAL_MARKS")
	private double totalMarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UserResponse> getUserResponses() {
		return userResponses;
	}

	public void setUserResponses(List<UserResponse> userResponses) {
		this.userResponses = userResponses;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public int getTotalNoOfQuestions() {
		return totalNoOfQuestions;
	}

	public void setTotalNoOfQuestions(int totalNoOfQuestions) {
		this.totalNoOfQuestions = totalNoOfQuestions;
	}

	public int getNoOfAnsweredQuestions() {
		return noOfAnsweredQuestions;
	}

	public void setNoOfAnsweredQuestions(int noOfAnsweredQuestions) {
		this.noOfAnsweredQuestions = noOfAnsweredQuestions;
	}

	public double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}
	

}
