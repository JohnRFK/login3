package com.john.login3.login3.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.john.login3.login3.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}
