package com.mattf.employeeskills.repository;

import com.mattf.employeeskills.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    void deleteById(long id);
}
