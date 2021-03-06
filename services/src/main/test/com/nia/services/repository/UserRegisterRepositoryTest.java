package com.nia.services.repository;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.annotation.ApplicationScope;

import com.nia.services.entity.ApplicationUser;
import com.nia.services.entity.UserRegister;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegisterRepositoryTest {
	
	@Autowired
	UserRegisterRepository registerRepository;
	
	@Autowired
	ApplicationUserRepository repo;
	
	@Test
	@Transactional
	public void testGEtone() {
		// ApplicationUser applicationUser = repo.getOne(1L);
		// System.out.println("test" + applicationUser);
		UserRegister register = registerRepository.getOne(2L);
		System.out.println(register.toString());
	}
}
