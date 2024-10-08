package com.example.springbase.controller;

import com.example.springbase.entity.User;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/self")
public class UserController extends GenericController<User, String> {
    @Autowired
    private UserService accountService;

    @Override
    public IService<User, String> getService() {
        return accountService;
    }

}
