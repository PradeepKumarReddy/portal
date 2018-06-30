package com.nia.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.services.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
