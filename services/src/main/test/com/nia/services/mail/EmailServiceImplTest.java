package com.nia.services.mail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private Environment env;

	@Test
	public void sendSimpleMessage() throws Exception {
        Mail mail = new Mail();
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setTo("narreddyp@gmail.com");
        mail.setSubject("Registration Successfully with Nakshatra Academy");
        System.out.println("sendSimpleMessage " + "123");
        Map<String, Object> model = new HashMap<>();
        model.put("regId", "123");
        mail.setModel(model);

        emailService.sendSimpleMessage(mail);
    }


}
