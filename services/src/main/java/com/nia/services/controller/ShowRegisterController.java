package com.nia.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.ShowRegister;
import com.nia.services.repository.ShowRegisterRepository;

@RestController
@RequestMapping("/api")
public class ShowRegisterController {
	
	@Autowired
	ShowRegisterRepository showRegisterRepo;
	
	@GetMapping("/get/showRegister")
	public ShowRegister getShowRegister() {
		return showRegisterRepo.getOne(1L);
	}

}
