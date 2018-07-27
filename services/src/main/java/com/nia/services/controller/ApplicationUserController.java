package com.nia.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.ApplicationUser;
import com.nia.services.repository.ApplicationUserRepository;

@RestController
@RequestMapping("/api")
public class ApplicationUserController {
	
	@Autowired
	private ApplicationUserRepository userRepository;
	
	@GetMapping("/get/allUsers")
	public List<ApplicationUser> getAllApplicationUsers() {
		return userRepository.findAll();
	}
	@PostMapping("/disableUser/{regId}")
	public ApplicationUser disableUser(@PathVariable String regId) {
		System.out.println("service regId "+ regId);
		ApplicationUser applicationUser = userRepository.findByUsername(regId);
		applicationUser.setEnabled(false);
		return userRepository.save(applicationUser);
	}
	@PostMapping("/enableUser/{regId}")
	public ApplicationUser enableUser(@PathVariable String regId) {
		ApplicationUser applicationUser = userRepository.findByUsername(regId);
		applicationUser.setEnabled(true);
		return userRepository.save(applicationUser);
	}

}
