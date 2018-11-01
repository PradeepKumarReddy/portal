package com.nia.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nia.services.entity.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	
    ApplicationUser findByUsername(String username);

    @Query("select au from ApplicationUser au where au.enabled=true and au.username=:username")
	ApplicationUser findByName(@Param("username") String username);
	Optional<ApplicationUser> findByResetToken(String resetToken);
	
	@Query("SELECT au.userRegister.email FROM ApplicationUser au WHERE au.enabled=1")
	List<String> getAllActiveEmails();
}
