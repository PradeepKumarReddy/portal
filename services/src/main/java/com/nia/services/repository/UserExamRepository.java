package com.nia.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nia.services.entity.UserExam;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {
	
    @Query("SELECT ue FROM UserExam ue WHERE ue.examId=:examId and ue.username=:username")
	public List<UserExam> getByIdAndUserName(@Param("examId") Long examId, @Param("username") String username);
    
}
