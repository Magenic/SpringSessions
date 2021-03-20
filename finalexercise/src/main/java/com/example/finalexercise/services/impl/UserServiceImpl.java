package com.example.finalexercise.services.impl;

import com.example.finalexercise.data.UserRepository;
import com.example.finalexercise.models.User;
import com.example.finalexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository repository;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = repository.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) throws UsernameNotFoundException {
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			repository.delete(user.get());
		}
		else {
			throw new UsernameNotFoundException(String.format("Invalid user id: {}", id));
		}
	}

	@Override
    public User save(User user) {
        return repository.save(user);
    }
}
