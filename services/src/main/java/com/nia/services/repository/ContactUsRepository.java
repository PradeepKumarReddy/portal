package com.nia.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.services.entity.ContactUs;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
	
}
