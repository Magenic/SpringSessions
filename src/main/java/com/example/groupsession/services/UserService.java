package com.example.groupsession.services;

import com.example.groupsession.models.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
}
