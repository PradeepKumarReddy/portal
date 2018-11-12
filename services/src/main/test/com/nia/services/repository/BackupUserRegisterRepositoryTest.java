package com.nia.services.repository;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nia.services.entity.BackupUserRegister;
import com.nia.services.entity.UserRegister;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackupUserRegisterRepositoryTest {
	
	@Autowired
	UserRegisterRepository registerRepository;
	
	@Autowired
	BackupUserRegisterRepository repo;
	
	@Test
	@Transactional
	public void saveBackupUserRegister() {
		UserRegister register = registerRepository.getOne(1L);
		
		BackupUserRegister backupUserRegister = new BackupUserRegister();
		
		BeanUtils.copyProperties(register, backupUserRegister);
		
		System.out.println(backupUserRegister.toString());
		
		repo.save(backupUserRegister);
		
	}
}
