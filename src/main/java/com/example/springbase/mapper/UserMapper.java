package com.example.springbase.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbase.dto.request.UserUpdateRequest;
import com.example.springbase.dto.response.UserResponse;
import com.example.springbase.entity.User;
import com.example.springbase.repository.UserRepository;

@Component
public class UserMapper {
    // @Autowired
    // private UserRepository userRepository;

    public User toUser (UserUpdateRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAvatarURL(request.getAvatarURL());
        
        return user;
    }

    public UserResponse torResponse (User user){
        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAvatarURL(user.getAvatarURL());
        return response;
    }
}
