package com.nia.services.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.Subject;
import com.nia.services.repository.SubjectRepository;

@RestController
@RequestMapping("/api")
public class SubjectController {
	
	@Autowired
	private SubjectRepository repo; 
	
	@GetMapping("/get/subject/{name}/{type}")
	public Subject getSubjectByNameAndType(@PathVariable String name, @PathVariable String type) {
		return repo.findByNameAndType(name, type);
	}
	
	@GetMapping("/get/allSubjects/{type}")
	public Set<Subject> getAllSubjectsByType(@PathVariable String type) {
		return repo.findAllByType(type);
	}
}
