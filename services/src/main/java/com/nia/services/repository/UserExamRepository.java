package com.nia.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nia.services.entity.Exam;
import com.nia.services.entity.UserExam;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {
	
    @Query("SELECT ue FROM UserExam ue WHERE ue.examId=:examId and ue.username=:username")
	public List<UserExam> getByIdAndUserName(@Param("examId") Long examId, @Param("username") String username);
    
    @Query("SELECT ue FROM UserExam ue WHERE ue.username=:username order by ue.id asc" )
    public List<UserExam> getCompletedExams(@Param("username") String username);
    
    // @Query("SELECT e.id, e.examDescription, e.examDate, e.examName, e.isActive, NOT ISNULL(NULLIF(ue.username,'')) as attended FROM Exam e LEFT JOIN UserExam ue ON e.id = ue.examId and ue.username=:username order by ue.id")
    @Query("SELECT new com.nia.services.entity.Exam(e.id, e.examDescription, e.examDate, e.examName, e.isActive, ue.totalNoOfQuestions, ue.noOfAnsweredQuestions, ue.totalMarks, ue.username) FROM Exam e LEFT JOIN UserExam ue ON e.id = ue.examId and ue.username=:username order by ue.id desc")
    public List<Exam> getAllExamsWithUserAttended(@Param("username") String username);
    
}
