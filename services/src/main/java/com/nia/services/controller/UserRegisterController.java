package com.nia.services.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.UserRegister;
import com.nia.services.exception.EmailExistsException;
import com.nia.services.mail.EmailServiceImpl;
import com.nia.services.mail.Mail;
import com.nia.services.repository.UserRegisterRepository;

@RestController
@RequestMapping("/api/user")
public class UserRegisterController {
	
	@Autowired
	private Environment env;
	
	private UserRegisterRepository registerRepository;
	
	@Autowired
	EmailServiceImpl emailServiceImpl; 

    public UserRegisterController(UserRegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @PostMapping("/register")
    public UserRegister register(@RequestBody UserRegister userRegister) throws EmailExistsException {
    	if (emailExist(userRegister.getEmail())) {
            throw new EmailExistsException
              ("There is an account with that email adress: " + userRegister.getEmail());
        }
    	UserRegister savedUser = registerRepository.saveAndFlush(userRegister);
    	savedUser = registerRepository.getOne(savedUser.getId());
    	return savedUser;
    }
    
    private boolean emailExist(String email) {
    	UserRegister registerUser = registerRepository.findByEmail(email);
    	boolean emailExists = (registerUser == null) ? false: true; 
    	return emailExists;
	}

	@GetMapping("/get/{id}")
    public UserRegister register(@PathVariable("id") Long id) {
    	UserRegister savedUser = registerRepository.getOne(id);
    	try {
			sendSimpleMessage(savedUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return registerRepository.getOne(savedUser.getId());
    }
    
    
    public void sendSimpleMessage(UserRegister savedUser) throws Exception {
        //log.info("Sending Email with Thymeleaf HTML Template Example");

        Mail mail = new Mail();
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setTo(savedUser.getEmail());
        mail.setSubject("Registration Successfully with Nakshatra Academy");
        System.out.println("sendSimpleMessage " + savedUser.getRegistrationId());
        Map<String, Object> model = new HashMap<>();
        model.put("regId", savedUser.getRegistrationId());
       // model.put("location", "Belgium");
       // model.put("signature", "https://memorynotfound.com");
        mail.setModel(model);

        emailServiceImpl.sendSimpleMessage(mail);
    }

}
