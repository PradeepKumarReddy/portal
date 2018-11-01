package com.nia.services.repository;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nia.services.entity.ContactUs;

import io.jsonwebtoken.lang.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactUsRepositoryTest {

	@Autowired
	private ContactUsRepository repo;

	@Test
	public void testSave() {
		Optional<ContactUs> contactUS = repo.findById(new Long(1));
		Assert.notNull(contactUS.get());
	}

}
