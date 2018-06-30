package com.nia.services.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nia.services.entity.Exam;
import com.nia.services.entity.Question;
import com.nia.services.entity.QuestionOption;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamRepositoryTest {

	@Autowired
	private ExamRepository repo;

	@Test
	public void testSave() {
		Exam exam = new Exam();
		exam.setExamName("Welcome Exam");
		
		Question question = new Question();
		question.setQuestionDesc("TEST");
		
		

		QuestionOption option1 = new QuestionOption();
		option1.setOptionDesc("OPT1");
		option1.setQuestion(question);
		question.getOptions().add(option1);

		QuestionOption option2 = new QuestionOption();
		option2.setOptionDesc("OPT2");
		// question.getOptions().add(option2);

		QuestionOption option3 = new QuestionOption();
		option3.setOptionDesc("OPT3");
		// question.getOptions().add(option3);

		QuestionOption option4 = new QuestionOption();
		option4.setOptionDesc("OPT4");
		// question.getOptions().add(option4);

		question.setExam(exam);
		exam.getQuestions().add(question);
		
		repo.save(exam);
	}

}
