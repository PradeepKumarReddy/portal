package com.nia.services.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.SubResource;
import com.nia.services.entity.Subject;
import com.nia.services.entity.UserRegister;
import com.nia.services.mail.EmailServiceImpl;
import com.nia.services.mail.Mail;
import com.nia.services.repository.ApplicationUserRepository;
import com.nia.services.repository.SubResourceRepository;
import com.nia.services.repository.SubjectRepository;
import com.nia.services.repository.UserRegisterRepository;

@RestController
@RequestMapping("/api")
public class SubjectController {
	
	@Autowired
	private SubjectRepository repo; 
	
	@Autowired
	private SubResourceRepository resourceRepo;
	
	@Autowired
	private Environment env;

	@Autowired
	private ApplicationUserRepository userRepository;

	@Autowired
	EmailServiceImpl emailServiceImpl;
	
	@GetMapping("/get/subject/{name}/{type}")
	public Subject getSubjectByNameAndType(@PathVariable String name, @PathVariable String type) {
		return repo.findByNameAndType(name, type);
	}
	
	@GetMapping("/get/allSubjects/{type}")
	public Set<Subject> getAllSubjectsByType(@PathVariable String type) {
		return repo.findAllByType(type);
	}
	
	@PostMapping("/subResource/add")
	public SubResource addResource(@RequestBody SubResource subResource) {
		Subject sub = repo.findByName(subResource.getSubject().getName());
		subResource.setSubject(sub);
		try {
			sendResourceUploadEmail(subResource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceRepo.save(subResource);
	}
	
	@GetMapping("/get/subjects")
	public List<Subject> getAllSubjects() {
		return repo.findAll();
	}
	
	public void sendResourceUploadEmail(SubResource subResource) throws Exception {

		Mail mail = new Mail();
		mail.setFrom(env.getProperty("spring.mail.username"));
		
		mail.getMultipleRecipients().addAll(userRepository.getAllActiveEmails());
		mail.setSubject("Nakshatra Academy Group2 Classes sent you a video: \"" + subResource.getName() + "\"");

		Map<String, Object> model = new HashMap<>();
		model.put("subjectName", subResource.getSubject().getName());
		model.put("videoName", subResource.getName());
		mail.setModel(model);

		emailServiceImpl.sendResourceUploadEmail(mail);
	}
}
