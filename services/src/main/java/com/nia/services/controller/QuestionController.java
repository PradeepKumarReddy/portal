package com.nia.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.Question;
import com.nia.services.entity.QuestionOption;
import com.nia.services.repository.QuestionRepository;

@RestController
@RequestMapping("/api")
public class QuestionController {
	
	@Autowired
	private QuestionRepository repo; 
	
	@PostMapping("/question/add")
	public Question addQuestion(@RequestBody Question question) {
		/*System.out.println(question);
		
		for(QuestionOption option : question.getOptions())
		{
			System.out.println(option.toString());
		}*/
		
		Question question2 = new Question();
		question2.setQuestionDesc(question.getQuestionDesc());
		
		for (QuestionOption option2 : question.getOptions()) {
			QuestionOption questionOption = new QuestionOption();
			questionOption.setAnswer(option2.isAnswer());
			questionOption.setOptionDesc(option2.getOptionDesc());
			questionOption.setQuestion(question2);
			question2.getOptions().add(questionOption);
		}
		
		
		return repo.save(question2);
	}

}
