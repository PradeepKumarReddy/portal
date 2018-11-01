package com.nia.services.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nia.services.entity.ApplicationUser;
import com.nia.services.entity.Privilege;
import com.nia.services.entity.Role;
import com.nia.services.repository.ApplicationUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private ApplicationUserRepository applicationUserRepository;

	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	public UserDetails loadUserByUsername_old(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByName(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), getAuthorities(applicationUser.getRoles()));
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			System.out.println("User id " + username + " doesn't exists ");
			throw new UsernameNotFoundException("User id " + username + " doesn't exists ");
		} else if (applicationUser != null) {
			if (! applicationUser.isEnabled()) {
				System.out.println("User id " + username + " doesn't exists ");
				throw new UsernameNotFoundException("User id " + username + " is active ");
			}
		}
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), getAuthorities(applicationUser.getRoles()));
	}
	

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
