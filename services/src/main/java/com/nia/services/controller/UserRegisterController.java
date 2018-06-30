package com.nia.services.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.UserRegister;
import com.nia.services.repository.UserRegisterRepository;

@RestController
@RequestMapping("/api/user")
public class UserRegisterController {
	
	private UserRegisterRepository registerRepository;

    public UserRegisterController(UserRegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @PostMapping("/register")
    public UserRegister register(@RequestBody UserRegister userRegister) {
    	UserRegister savedUser = registerRepository.save(userRegister);
    	System.out.println(savedUser.toString());
    	savedUser = registerRepository.getOne(savedUser.getId());
    	System.out.println(savedUser.toString());
    	//System.out.println(registerRepository.getOne(savedUser.getId()).getRegistrationId());
    	return registerRepository.getOne(savedUser.getId());
    }
    
    @GetMapping("/get/{id}")
    public UserRegister register(@PathVariable("id") Long id) {
    	//UserRegister savedUser = registerRepository.save(userRegister);
    	//System.out.println(savedUser.toString());
    	UserRegister savedUser = registerRepository.getOne(id);
    	System.out.println(savedUser.toString());
    	//System.out.println(registerRepository.getOne(savedUser.getId()).getRegistrationId());
    	return registerRepository.getOne(savedUser.getId());
    }

}
