package com.example.springbase.service;

import com.example.springbase.dto.TokenDTO;
import com.example.springbase.entity.User;
import com.example.springbase.generic.IService;
import com.example.springbase.record.RegisterRecord;
import com.example.springbase.record.SignInRecord;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User, String>, UserDetailsService {
    TokenDTO signIn(SignInRecord record);

    User signUp(RegisterRecord record);

    User findOne(String username);

    User getAuthenticatedAccount();

    Boolean isSendMailForgetPassword(String email);

    User findByEmail(String email);

    Boolean verifyEmail(String email, String otp);
}
