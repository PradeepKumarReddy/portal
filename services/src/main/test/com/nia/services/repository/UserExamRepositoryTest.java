package com.nia.services.repository;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
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

	@Test
	public void testGetAllExamsWithUserAttended() {
		List<Exam> exams = userExamRepository.getAllExamsWithUserAttended("NAC20180001");

		System.out.println(exams.size());
	} 
	
	@Test
	public void testExamsIdSorted() {
		List<Exam> exams = new ArrayList<>();
		
		Exam ex1 = new Exam();
		ex1.setId(10L);
		exams.add(ex1);
		
		Exam ex2 = new Exam();
		ex2.setId(11L);
		exams.add(ex2);
		
		Exam ex3 = new Exam();
		ex3.setId(2L);
		exams.add(ex3);
		
		Exam ex4 = new Exam();
		ex4.setId(1L);
		exams.add(ex4);
		
		for (Exam e : exams) {
			System.out.println(e.getId());
		}
		
		Exam ex5 = new Exam();
		ex5.setId(-1L);
		exams.add(ex5);
		
		Collections.sort(exams);
		
		for (Exam e : exams) {
			System.out.println(e.getId());
		}
		
	}
}
