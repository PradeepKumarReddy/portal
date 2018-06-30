package com.nia.services.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nia.services.entity.ApplicationUser;
import com.nia.services.entity.UserRegister;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class ApplicationUserRepositoryTest {
	
	@Autowired private ApplicationUserRepository userRepo;
	@Autowired private UserRegisterRepository userRegisterRepo;

    //@Test
    public void testInsert() {
        ApplicationUser appUser = new ApplicationUser();
        appUser.setUsername("pradeep");
        appUser.setPassword("test");
        UserRegister userRegister = new UserRegister();
        userRegister.setFirstName("PRADEEP");
        userRegister.setLastName("N");
        userRegister.setEmail("narreddyp.gmail.com");
        userRegisterRepo.save(userRegister);
        appUser.setUserRegister(userRegister);
        ApplicationUser userInserted = userRepo.save(appUser);
        //userRepo.flush();
        
        assertThat(userInserted.getUsername(), equalTo("pradeep"));
        /*String name = jdbcTemplate.queryForObject("SELECT cont_name FROM cont WHERE cont_id = ?", 
                                                  String.class,
                                                  continentInserted.getId());
        assertThat(name, equalTo("another"));*/
    }
    
    @Test
    public void testInsertUserRegister() {
    	ApplicationUser appUser = new ApplicationUser();
        appUser.setUsername("pradeep");
        appUser.setPassword("test");
        UserRegister userRegister = new UserRegister();
        userRegister.setFirstName("PRADEEP");
        userRegister.setLastName("N");
        userRegister.setEmail("narreddyp.gmail.com");
        userRegister = userRegisterRepo.save(userRegister);
        System.out.println(userRegister.toString());
    	assertNotNull(userRegister.getRegistrationId());
    }
}
