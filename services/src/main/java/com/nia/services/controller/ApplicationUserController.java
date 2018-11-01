package com.nia.services.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.Application;
import com.nia.services.entity.ApplicationUser;
import com.nia.services.entity.Role;
import com.nia.services.entity.UserRegister;
import com.nia.services.mail.EmailServiceImpl;
import com.nia.services.mail.Mail;
import com.nia.services.repository.ApplicationUserRepository;
import com.nia.services.repository.RoleRepository;
import com.nia.services.repository.UserRegisterRepository;

@RestController
@RequestMapping("/api")
public class ApplicationUserController {
	private static final Logger logger = LogManager.getLogger(Application.class);
	
	@Autowired
	private Environment env;

	@Autowired
	private ApplicationUserRepository userRepository;

	@Autowired
	private UserRegisterRepository registerRepository;
	
	@Autowired
    private RoleRepository roleRepository;

	@Autowired
	EmailServiceImpl emailServiceImpl;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public ApplicationUserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@GetMapping("/get/allUsers")
	public List<ApplicationUser> getAllApplicationUsers() {
		logger.info("getAllApplicationUsers");
		return userRepository.findAll();
	}
	
	@GetMapping("/getUser/{regId}")
	public ApplicationUser getUserByUsername(@PathVariable String regId) {
		ApplicationUser applicationUser = userRepository.findByUsername(regId);
		return applicationUser;
	}

	@GetMapping("/isUserAdmin/{regId}")
	public boolean isUserAdmin(@PathVariable String regId) {
		System.out.println("isUserAdmin");
		ApplicationUser applicationUser = userRepository.findByUsername(regId);
		boolean isUserAdmin = false;
		if (applicationUser != null) {
			for (Role role : applicationUser.getRoles()) {
				if ("ROLE_ADMIN".equalsIgnoreCase(role.getName())) {
					isUserAdmin = true;
					System.out.println(isUserAdmin);
					break;
				}
			}
		}
		return isUserAdmin;
	}
	
	@PostMapping("/disableUser/{regId}")
	public ApplicationUser disableUser(@PathVariable String regId) {
		System.out.println("service regId " + regId);
		ApplicationUser applicationUser = userRepository.findByUsername(regId);
		applicationUser.setEnabled(false);
		applicationUser = userRepository.save(applicationUser);
		return applicationUser;
	}

	@PostMapping("/enableUser/{regId}")
	public ApplicationUser enableUser(@PathVariable String regId) {
		ApplicationUser applicationUser = userRepository.findByUsername(regId);
		applicationUser.setEnabled(true);
		applicationUser = userRepository.save(applicationUser);
		try {
			sendUserActivateEmail(applicationUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applicationUser;
	}
	
	private ApplicationUser saveAppUser(UserRegister userRegister) {
		ApplicationUser user = new ApplicationUser();
		user.setUsername(userRegister.getRegistrationId());
        Role adminRole = roleRepository.findByName("ROLE_USER");
        List<Role> list = new ArrayList<Role>();
        list.add(adminRole); 
        user.setRoles(list);
        user.setEnabled(false);
        user.setUserRegister(userRegister);
        user = userRepository.saveAndFlush(user);
        return user;
	}

	@PostMapping("/resetPasswordEmail/{regId}")
	public boolean resetPasswordEmail(@PathVariable String regId) {
		UserRegister userRegister = registerRepository.findByRegistrationId(regId);
		ApplicationUser applicationUser = null;
		if(userRegister != null) {
			applicationUser = userRepository.findByUsername(regId);
			if (applicationUser == null) {
				applicationUser = saveAppUser(userRegister);
				// applicationUser = userRepository.findByUsername(regId);
			}
		}
		if (applicationUser == null) {
			return false;
		} else {
			// Generate random 36-character string token for reset password
			applicationUser.setResetToken(UUID.randomUUID().toString());

			// Save token to database
			userRepository.save(applicationUser);

			String appUrl = env.getProperty("resetPassword.url") + applicationUser.getResetToken();

			try {
				sendResetPasswordEmail(userRegister, appUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return true;
	}
	
	private void sendUserActivateEmail(ApplicationUser applicationUser) throws Exception {
		Mail mail = new Mail();
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setTo(applicationUser.getUserRegister().getEmail());
		mail.setSubject("Nakshatra Academy - Your Registration Id is activated now");
		System.out.println("sendUserActivateEmail " + applicationUser.getUsername());
		Map<String, Object> model = new HashMap<>();
		model.put("regId", applicationUser.getUsername());
		mail.setModel(model);
		emailServiceImpl.sendUserActivatedEmail(mail);
	}

	public void sendResetPasswordEmail(UserRegister savedUser, String appUrl) throws Exception {
		// log.info("Sending Email with Thymeleaf HTML Template Example");

		Mail mail = new Mail();
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setTo(savedUser.getEmail());
		mail.setSubject("Password Reset Request with Nakshatra Academy");
		System.out.println("sendSimpleMessage " + savedUser.getRegistrationId());
		Map<String, Object> model = new HashMap<>();
		model.put("regId", savedUser.getRegistrationId());
		model.put("appUrl", appUrl);
		// model.put("location", "Belgium");
		// model.put("signature", "https://memorynotfound.com");
		mail.setModel(model);

		emailServiceImpl.sendResetPasswordEmail(mail);
	}
	
	@PostMapping("/reset")
    public ApplicationUser getResetUser (
        @RequestParam(required = true) String token) {
		Optional<ApplicationUser> applicationUser = userRepository.findByResetToken(token);
        return applicationUser.get();
    }
	
	@PostMapping("/updatePassword")
	public ApplicationUser updateUser(@RequestBody ApplicationUser applicationUser) {
		ApplicationUser newUser = userRepository.findByUsername(applicationUser.getUsername());
		newUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
		return userRepository.save(newUser);
	}
}
