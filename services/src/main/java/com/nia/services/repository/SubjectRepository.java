package com.nia.services.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nia.services.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	@Query("select s from Subject s, SubResource sr where s.id = sr.subject and s.name=:name and sr.resourceType=:type")
	public Subject findByNameAndType(@Param("name") String name, @Param("type") String type);
	
	public Subject findByName(String name);
	
	@Query("select s from Subject s, SubResource sr where s.id = sr.subject and sr.resourceType=:type")
	public Set<Subject> findAllByType(@Param("type") String type);
	
}
