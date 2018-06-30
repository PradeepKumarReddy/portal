package com.nia.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.services.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
