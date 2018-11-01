package com.nia.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.ContactUs;
import com.nia.services.repository.ContactUsRepository;

@RestController
@RequestMapping("/api")
public class ContactUsController {
	
	@Autowired
	ContactUsRepository contactUsRepository;
	
	@GetMapping("/get/contactUs")
	public ContactUs getContactUs() {
		return contactUsRepository.getOne(1L);
	}

}
