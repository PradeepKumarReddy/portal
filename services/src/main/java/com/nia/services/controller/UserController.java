package com.nia.services.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nia.services.entity.ApplicationUser;
import com.nia.services.entity.Role;
import com.nia.services.entity.UserRegister;
import com.nia.services.repository.ApplicationUserRepository;
import com.nia.services.repository.PrivilegeRepository;
import com.nia.services.repository.RoleRepository;
import com.nia.services.repository.UserRegisterRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserRegisterRepository registerRepository;
    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private PrivilegeRepository privilegeRepository;

    public UserController(ApplicationUserRepository applicationUserRepository, UserRegisterRepository registerRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.registerRepository = registerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ApplicationUser signUp(@RequestBody ApplicationUser user) {
    	UserRegister userRegister = registerRepository.findByRegistrationId(user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        user.setUserRegister(userRegister);
        user = applicationUserRepository.save(user);
        return user;
    }
}
