package com.nia.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.services.entity.UserRegister;

public interface UserRegisterRepository extends JpaRepository<UserRegister, Long> {
	UserRegister findByFirstName(String firstName);
	UserRegister findByRegistrationId(String registrationId);
}
