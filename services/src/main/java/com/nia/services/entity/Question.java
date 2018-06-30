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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="QUESTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question {
	
	@Id
	@Column(name = "question_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="question_desc")
	private String questionDesc;
	
	@OneToMany(
			cascade = CascadeType.ALL, 
			orphanRemoval = true,
			mappedBy="question"
    )
	@JsonManagedReference
	private Set<QuestionOption> options = new HashSet<>();
	
	@Column(name="is_active")
	private boolean isActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
	@JsonBackReference
    private Exam exam;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public Set<QuestionOption> getOptions() {
		return options;
	}

	public void setOptions(Set<QuestionOption> options) {
		this.options = options;
	}
	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionDesc=" + questionDesc + ", options=" + options + ", getId()=" + getId()
				+ ", getQuestionDesc()=" + getQuestionDesc() + ", getOptions()=" + getOptions() + "]";
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Transient
	private String resultDesc; 

	@Transient
	private boolean correctAnswered = false;

	@Transient
	private boolean answered = false;
	
	public void setResultDesc(String value) {
		this.resultDesc = value;
	}

	public boolean isCorrectAnswered() {
		return correctAnswered;
	}

	public void setCorrectAnswered(boolean correctAnswered) {
		this.correctAnswered = correctAnswered;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	
}
