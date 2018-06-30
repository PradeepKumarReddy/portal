package com.nia.services.repository;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nia.services.entity.Exam;
import com.nia.services.entity.UserExam;
import com.nia.services.entity.UserResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserExamRepositoryTest {
	
	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private UserExamRepository userExamRepository;
	
	@Test
	public void testSaveUserExam() {
		Exam exam = new Exam();
		exam.setExamName("Welcome Exam");
		exam.setActive(true);
		
		exam = examRepo.save(exam);
		assertNotNull(exam.getId());
		
		UserExam userExam = new UserExam();
		userExam.setUsername("TEST");
		userExam.setExamId(exam.getId());
		
		UserResponse response = new UserResponse();
		response.setQuestionId(1L);
		response.setOptionId(1L);
		response.setUserexam(userExam);
		
		UserResponse response1 = new UserResponse();
		response1.setQuestionId(1L);
		response1.setOptionId(1L);
		response1.setUserexam(userExam);
		
		userExam.getUserResponses().add(response);
		userExam.getUserResponses().add(response1);
		
		userExam = userExamRepository.save(userExam);
		
		assertNotNull(userExam.getId());
		
	}
	
	@Test
	public void testLoad() {
		List<UserExam> userExams = userExamRepository.getByIdAndUserName(1L, "NA0002");
		assertNotNull(userExams);
	}

}
