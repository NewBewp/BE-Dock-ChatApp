package com.example.springbase.repository;

import com.example.springbase.entity.Role;
import com.example.springbase.generic.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends IRepository<Role, String> {
}
