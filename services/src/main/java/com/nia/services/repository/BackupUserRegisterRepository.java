package com.nia.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.services.entity.BackupUserRegister;

public interface BackupUserRegisterRepository extends JpaRepository<BackupUserRegister, Long> {
}
