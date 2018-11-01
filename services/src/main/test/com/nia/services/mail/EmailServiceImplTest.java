package com.nia.services.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
	
	@Test
	public void sendResourceNotification() throws Exception {
		List<String>  list = new ArrayList<>();
        list.add("narreddyp@gmail.com");
        list.add("npkr.hdp@gmail.com");
        
        Mail mail = new Mail();
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setTo("narreddyp@gmail.com");
        mail.setSubject("Registration Successfully with Nakshatra Academy");
        System.out.println("sendResourceNotification " + "123");
        Map<String, Object> model = new HashMap<>();
        model.put("subjectName", "History");
        model.put("videoName", "Class1");
        
        mail.getMultipleRecipients().addAll(list);
        
        mail.setModel(model);

        emailService.sendResourceUploadEmail(mail);
	}


	@Test
	public void validateEmailAddress() {
		String email = "Kollianuja2016@gmail.com";
		boolean isValid = false;
		try {
			//
			// Create InternetAddress object and validated the supplied
			// address which is this case is an email address.
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			System.out.println("You are in catch block -- Exception Occurred for: " + email);
		}
		System.out.println(isValid);
	}
}
