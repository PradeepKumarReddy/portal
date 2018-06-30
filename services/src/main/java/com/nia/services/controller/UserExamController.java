package com.nia.services.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.Exam;
import com.nia.services.entity.Question;
import com.nia.services.entity.QuestionOption;
import com.nia.services.entity.UserExam;
import com.nia.services.entity.UserResponse;
import com.nia.services.repository.ExamRepository;
import com.nia.services.repository.UserExamRepository;

@RestController
@RequestMapping("/api")
public class UserExamController {
	
	@Autowired
	UserExamRepository userExamRepository;
	
	@Autowired
	private ExamRepository repo; 
	
	@GetMapping("/userExam/{examId}/{username}")
	public UserExam getUserExam(@RequestParam Long examId, @RequestParam String username) {
		List<UserExam> listExams = userExamRepository.getByIdAndUserName(examId, username);
		
		return listExams.get(0);
	}
	
	@PostMapping("/userExam/add")
	public UserExam addUserExam(@RequestBody UserExam userExam)
	{
		return userExamRepository.save(userExam);
	}
	
	@PostMapping("/userExam/update")
	public Exam updateUserExam(@RequestBody UserExam userExam)
	{
		System.out.println("updateUserExam called");
		UserExam resultExam = userExamRepository.save(userExam);
		
		Exam exam = repo.getOne(userExam.getExamId());
		
		
			
			for(UserResponse response : resultExam.getUserResponses()) {
				
				Question resQues = getQestionById(response.getQuestionId(), exam.getQuestions());
				
				
				if(response.getOptionId() != null && response.getQuestionId() == resQues.getId()) {
					//ques.setResultDesc("Your Answer is Correct");
					resQues.setAnswered(true);
					//resQues.setCorrectAnswered(true);
					for(QuestionOption opt : resQues.getOptions()) {
						if (response.getOptionId() == opt.getId()) {
							opt.setUserSelect(true);
						}
						if(opt.isAnswer() && opt.getId().equals(response.getOptionId())) {
							resQues.setCorrectAnswered(true);
						}
					}
				}
			}
		
		return exam;
	}
	
	private Question getQestionById(Long quesId, Set<Question> questions) {
		for (Question ques : questions) {
			if(ques.getId().equals(quesId)) 
				return ques;
		}
		
		return null;
	}
	
	

}
