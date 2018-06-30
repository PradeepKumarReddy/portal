package com.nia.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.services.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);

}
