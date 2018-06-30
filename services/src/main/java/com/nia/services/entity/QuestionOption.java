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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="QUESTION_OPTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuestionOption {

	@Id
	@Column(name = "option_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="option_desc")
	private String optionDesc;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
	@JsonBackReference
    private Question question;
	
	@Column(name="is_answer")
	private boolean answer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOptionDesc() {
		return optionDesc;
	}

	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "QuestionOption [id=" + id + ", optionDesc=" + optionDesc + ", question=" + question + "]";
	}
	

	@Transient
	private boolean userSelect = false;

	public boolean isUserSelect() {
		return userSelect;
	}

	public void setUserSelect(boolean userSelect) {
		this.userSelect = userSelect;
	}

	
}
