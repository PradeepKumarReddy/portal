package com.nia.services.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.Exam;
import com.nia.services.entity.Question;
import com.nia.services.entity.QuestionOption;
import com.nia.services.entity.UserExam;
import com.nia.services.entity.UserResponse;
import com.nia.services.repository.ExamRepository;
import com.nia.services.repository.UserExamRepository;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

@RestController
@RequestMapping("/api")
public class UserExamController {
	
	@Autowired
	UserExamRepository userExamRepository;
	
	@Autowired
	private ExamRepository repo; 
	
	@GetMapping("/userExam/{examId}/{username}")
	public UserExam getUserExam(@PathVariable Long examId, @PathVariable String username) {
		UserExam ret = null;
		try {
		List<UserExam> listExams = userExamRepository.getByIdAndUserName(examId, username);
		ret = listExams.get(0);
		} catch(Exception e) {
			System.out.println(" getUserExam examId " + examId + "username " + username);
			e.printStackTrace();
		}
		
		return ret;
	}
	
	@GetMapping("/userExam/completed/{username}")
	public List<UserExam> getCompletedExamsByUserId(@PathVariable String username) {
		return userExamRepository.getCompletedExams(username);
	}
	
	@PostMapping("/userExam/add")
	public UserExam addUserExam(@RequestBody UserExam userExam)
	{
		return userExamRepository.save(userExam);
	}
	
	@GetMapping("/examsList/completed/{username}")
	public List<Exam> getAllExamsWithUserAttended(@PathVariable String username) {
		return userExamRepository.getAllExamsWithUserAttended(username);
	}
	
	// @GetMapping("/userExam/completedDetails/{userExamId}/{username}")
	public Exam completedExam_old(@PathVariable Long userExamId, @PathVariable String username) {
		UserExam resultExam = userExamRepository.getOne(userExamId);
		
		Exam exam = repo.getOne(resultExam.getExamId());

		for (UserResponse response : resultExam.getUserResponses()) {

			Question resQues = getQestionById(response.getQuestionId(), exam.getQuestions());

			if (response.getOptionId() != null && response.getQuestionId() == resQues.getId()) {
				// ques.setResultDesc("Your Answer is Correct");
				resQues.setAnswered(true);
				// resQues.setCorrectAnswered(true);
				for (QuestionOption opt : resQues.getOptions()) {
					if (response.getOptionId() == opt.getId()) {
						opt.setUserSelect(true);
					}
					if (opt.isAnswer() && opt.getId().equals(response.getOptionId())) {
						resQues.setCorrectAnswered(true);
					}
				}
			}
		}
		return exam;
	}
	
	@GetMapping("/userExam/completedDetails/{userExamId}/{username}")
	public Exam completedExam(@PathVariable Long userExamId, @PathVariable String username) {
		UserExam resultExam = userExamRepository.getOne(userExamId);

		Exam exam = repo.getOne(resultExam.getExamId());
		try {
		prepareResultExam(resultExam, exam, false);
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return exam;
	}
	
	// @PostMapping("/userExam/update")
	public Exam updateUserExam_old(@RequestBody UserExam userExam)
	{
		System.out.println("updateUserExam called");
		UserExam resultExam = userExamRepository.save(userExam);
		
		Exam exam = repo.getOne(userExam.getExamId());
			for(UserResponse response : resultExam.getUserResponses()) {
				Question resQues = getQestionById(response.getQuestionId(), exam.getQuestions());
				
				if(response.getOptionId() != null && response.getQuestionId() == resQues.getId()) {
					resQues.setAnswered(true);
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
	
	
	@PostMapping("/userExam/update")
	public Exam updateUserExam(@RequestBody UserExam userExam)
	{
		System.out.println("updateUserExam called");
		UserExam resultExam = userExamRepository.save(userExam);
		
		Exam exam = repo.getOne(userExam.getExamId());
		try {
		prepareResultExam(resultExam, exam, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exam;
	}

	private void prepareResultExam_old(UserExam resultExam, Exam exam) {
		// Map<Long, List<QuestionOption>> questionAnswers = new HashMap<Long, List<QuestionOption>>();
		Map<Long, List<Long>> questionAnswers = new HashMap<Long, List<Long>>();
		
		for(Question question : exam.getQuestions()) {
			List<Long> answerOptions = new ArrayList<Long>();
			
			for(QuestionOption option : question.getOptions()) {
				if (option.isAnswer()) {
					// System.out.println(option.getId());
					answerOptions.add(option.getId());
				}
			}
			questionAnswers.put(question.getId(), answerOptions);
		}
		
		Map<Long, List<Long>> userAnswers = new HashMap<Long, List<Long>>();
		
		for(UserResponse response : resultExam.getUserResponses()) {
			if(userAnswers.get(response.getQuestionId()) == null) {
				// System.out.println("userAnswers first time " + response.getQuestionId());
				List<Long> userResponses = new ArrayList<>();
				userResponses.add(response.getOptionId());
				userAnswers.put(response.getQuestionId(), userResponses);
			} else {
				// System.out.println("userAnswers second time " + response.getQuestionId());
				userAnswers.get(response.getQuestionId()).add(response.getOptionId());
			}
		}
			
		for(Question question : exam.getQuestions()) {
			
			List<Long> answers = questionAnswers.get(question.getId());
			List<Long> responses = userAnswers.get(question.getId());
			// System.out.println(" anwsers question.getId() " + question.getId() + " "+ Arrays.toString(answers.toArray()));
			// System.out.println(" responses question.getId() " + question.getId() + " "+ Arrays.toString(responses.toArray()));
			for(QuestionOption opt: question.getOptions()) {
				if(responses != null && responses.contains(opt.getId())) {
					opt.setUserSelect(true);
				}
			}
			
			boolean allAnswered = answers.stream().allMatch(num -> responses.contains(num));
			 System.out.println("If all numbers from answers are present in responses :" 
					 + allAnswered);
			 if(allAnswered) {
				 question.setCorrectAnswered(true);
			 }
		
		}
	}
	
	private Question getQestionById(Long quesId, List<Question> list) {
		for (Question ques : list) {
			if(ques.getId().equals(quesId)) 
				return ques;
		}
		
		return null;
	}

	private void prepareResultExam(UserExam resultExam, Exam exam, boolean update) {

		Map<Long, List<Long>> questionAnswers = new HashMap<Long, List<Long>>();
		
		for(Question question : exam.getQuestions()) {
			questionAnswers.put(question.getId(), question.getOptions().stream().filter(opt -> opt.isAnswer()).map(opt -> opt.getId()).collect(Collectors.toList()));
		}
		
		// Prepare Question Responses by User
		
		Map<Long, List<UserResponse>> userAnswers = resultExam.getUserResponses().stream()
	       .collect(Collectors.groupingBy(
	    		UserResponse :: getQuestionId,
	    		Collectors.toList()));
	            // Collectors.toCollection(ArrayList::new)));
		int correctAnswers = 0;
			
		for(Question question : exam.getQuestions()) {
			
			List<Long> answers = questionAnswers.get(question.getId());
			boolean allAnswered = false;
			System.out.println(userAnswers.get(question.getId()));
			if (userAnswers.get(question.getId()) != null) {
			List<Long> responses = userAnswers.get(question.getId()).stream().map( res -> res.getOptionId()).collect(Collectors.toList());
			for(QuestionOption opt: question.getOptions()) {
				if(responses != null && responses.contains(opt.getId())) {
					opt.setUserSelect(true);
				}
			}
			
			allAnswered = answers.stream().allMatch(num -> responses.contains(num));
			}
			if(allAnswered) {
			 question.setCorrectAnswered(true);
			 correctAnswers++;
			}
		
		}
		
		if(update) {
			resultExam.setTotalNoOfQuestions(exam.getQuestions().size());
			resultExam.setNoOfAnsweredQuestions(correctAnswers);
			userExamRepository.save(resultExam);
		}
		
		exam.setNoOfAnsweredQuestions(resultExam.getNoOfAnsweredQuestions());
		exam.setTotalNoOfQuestions(resultExam.getTotalNoOfQuestions());
	}

}
