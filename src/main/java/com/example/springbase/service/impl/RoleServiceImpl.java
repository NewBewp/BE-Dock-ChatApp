package com.example.springbase.service.impl;

import com.example.springbase.entity.Role;
import com.example.springbase.generic.IRepository;
import com.example.springbase.repository.RoleRepository;
import com.example.springbase.service.AbstractService;
import com.example.springbase.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractService<Role, String> implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected IRepository<Role, String> getRepository() {
        return roleRepository;
    }
}
