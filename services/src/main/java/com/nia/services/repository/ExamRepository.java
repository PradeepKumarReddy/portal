package com.nia.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nia.services.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
	
	@Query("SELECT e FROM Exam e WHERE e.isActive=true and e.id not in (SELECT ue.examId FROM UserExam ue where ue.username=:username)")
	public List<Exam> getActiveExams(@Param("username") String username);

}
