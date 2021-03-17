package com.magenic.springboot.finals.service;

import com.magenic.springboot.finals.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface UserService {

    public User save(User user);
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException;
}
