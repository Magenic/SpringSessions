package com.magenic.springboot.finals.repository;

import com.magenic.springboot.finals.models.User;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
