package com.nia.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.services.entity.SubResource;

public interface SubResourceRepository extends JpaRepository<SubResource, Long> {
}
