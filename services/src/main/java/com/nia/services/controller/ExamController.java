package com.nia.services.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.Exam;
import com.nia.services.entity.Question;
import com.nia.services.repository.ExamRepository;
import com.nia.services.repository.QuestionRepository;

@RestController
@RequestMapping("/api")
public class ExamController {
	
	@Autowired
	private ExamRepository repo; 
	
	@Autowired
	private QuestionRepository quesRepo; 
	
	@PostMapping("/exam/add")
	public Exam addQuestion(@RequestBody Exam exam) {
		System.out.println(exam);
		Set<Question> questions = new HashSet<>(exam.getQuestions());
		exam.getQuestions().clear();
		for(Question ques : questions) {
			Question question = quesRepo.getOne(ques.getId());
			question.setExam(exam);
			exam.getQuestions().add(question);
		}
		/*		
		Question question2 = new Question();
		question2.setQuestionDesc(question.getQuestionDesc());
		
		for (QuestionOption option2 : question.getOptions()) {
			QuestionOption questionOption = new QuestionOption();
			questionOption.setOptionDesc(option2.getOptionDesc());
			questionOption.setQuestion(question2);
			question2.getOptions().add(questionOption);
		}*/
		
		return repo.save(exam);
	}
	
	@GetMapping("/get/activeExams/{registrationId}")
	public List<Exam> getActiveExams(@PathVariable String registrationId) {
		return repo.getActiveExams(registrationId);
	}
	
	@GetMapping("/exam/get/{id}")
	public Exam getById(@PathVariable("id") Long id) {
		return repo.getOne(id);
	}

}
