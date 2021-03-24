package com.robertw.EmployeeSkillsTracker.service.interfaces;

import com.robertw.EmployeeSkillsTracker.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void delete(User user);
    User save(User user);
}
