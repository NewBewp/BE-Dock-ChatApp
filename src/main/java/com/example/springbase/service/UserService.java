package com.example.springbase.service;

import com.example.springbase.dto.TokenDTO;
import com.example.springbase.dto.request.UserUpdateRequest;
import com.example.springbase.dto.response.UserResponse;
import com.example.springbase.entity.User;
import com.example.springbase.generic.IService;
import com.example.springbase.record.EmailSignInRecord;
import com.example.springbase.record.RegisterRecord;
import com.example.springbase.record.SignInRecord;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User, String>, UserDetailsService {
    TokenDTO signIn(SignInRecord record);

    User signUp(RegisterRecord record);

    User signUpWithEmail (String email);

    User findByUsername(String username);

    User getAuthenticatedAccount();

    Boolean isSendMailForgetPassword(String email);

    User findByEmail(String email);

    Boolean verifyEmail(String email, String otp);

    TokenDTO signInWithEmail(EmailSignInRecord record);

    void requestOtpForLogin (String email);

    UserResponse updateUser(String id, UserUpdateRequest request);

}
