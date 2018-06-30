package com.nia.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.services.entity.UserResponse;

public interface UserResponseRepositry extends JpaRepository<UserResponse, Long>{

}
