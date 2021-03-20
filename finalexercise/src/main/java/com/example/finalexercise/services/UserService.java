package com.example.finalexercise.services;

import com.example.finalexercise.models.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
}
