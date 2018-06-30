package com.nia.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="USER_RESPONSE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserResponse {

	@Id
	@Column(name = "USER_RESPONSE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_EXAM_ID")
	@JsonBackReference
    private UserExam userexam;
	
	@Column(name = "QUESTION_ID")
	private Long questionId;
	
	@Column(name = "OPTION_ID")
	private Long optionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public UserExam getUserexam() {
		return userexam;
	}

	public void setUserexam(UserExam userexam) {
		this.userexam = userexam;
	}
	
}
