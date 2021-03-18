package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.common.UserRole;
import com.example.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		return userRepository.findByUsername(userId)
				.map(user -> {
							return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getRole()));
					})
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		
		
	}

	private List<SimpleGrantedAuthority> getAuthority(String role) {

		if (role != null && UserRole.valueOf(role) != null) {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
		}

		return null;
	}

}
