package com.mattf.employeeskills.service;

import com.mattf.employeeskills.entity.User;

public interface UserService {
    void deleteById(long id);

    User save(User user);
}
