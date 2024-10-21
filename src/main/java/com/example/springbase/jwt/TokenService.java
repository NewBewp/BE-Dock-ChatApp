package com.example.springbase.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.springbase.service.UserService;

@Service
public class TokenService {
    @Autowired
    private JwtService jwtService;

    @Autowired    
    private UserService userService;

    public String refreshToken(String token) {
        if (jwtService.isTokenExpired(token)) {
            throw new RuntimeException("Token is expired");
        }
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = userService.loadUserByUsername(username);
        return jwtService.generateToken(userDetails);
    }
}
