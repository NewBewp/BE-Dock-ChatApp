package com.example.springbase.repository;

import com.example.springbase.entity.User;
import com.example.springbase.generic.IRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends IRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail (String email);
    
}