package com.nia.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nia.services.entity.Question;
import com.nia.services.entity.UserExam;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("SELECT q FROM Question q WHERE q.exam is null order by q.id")
	public List<Question> getAllQuestionsNotMappedToExam();
	
}
