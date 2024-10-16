package com.example.springbase.controller;

import com.example.springbase.dto.TokenDTO;
import com.example.springbase.entity.User;
import com.example.springbase.exception.ErrorHandler;
import com.example.springbase.exception.ExceptionResponse;
import com.example.springbase.jwt.JwtService;
import com.example.springbase.model.RequestResponse;
import com.example.springbase.record.EmailSignInRecord;
import com.example.springbase.record.RegisterRecord;
import com.example.springbase.record.SignInRecord;
import com.example.springbase.record.VerifyEmailRecord;
import com.example.springbase.service.MailService;
import com.example.springbase.service.UserService;
import com.example.springbase.util.AuthConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    @PreAuthorize(AuthConstants.NONE)
    public ResponseEntity<?> login(@RequestBody SignInRecord loginRecord) {
        TokenDTO dto = userService.signIn(loginRecord);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse("Sign in successful", dto));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse("Invalid username or password"));
    }

    @PostMapping("/sign-up")
    @PreAuthorize(AuthConstants.NONE)
    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterRecord record) {
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(
                "Register successfully, please check your email to verify account.", userService.signUp(record)));
    }

    @PostMapping("/sign-out")
    @PreAuthorize(AuthConstants.ALL)
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // JwtBlacklistToken blacklistToken = new JwtBlacklistToken();
        // blacklistToken.setToken(request.getHeader("Authorization").substring(7));
        // blacklistToken.setExpirationDate(new Timestamp(jwtService
        // .extractExpiration(request
        // .getHeader("Authorization")
        // .substring(7)).getTime()));
        // jwtBlacklistTokenRepository.save(blacklistToken);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse("Logout successfully."));
    }

    @PostMapping("/forget-password")
    @PreAuthorize(AuthConstants.NONE)
    public ResponseEntity<?> forgetPassword(@RequestParam String email) {
        if (userService.isSendMailForgetPassword(email)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RequestResponse("Please check your email to change your password."));
        } else {
            throw new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to send change password link.");
        }
    }

    @PostMapping("/verify-email")
    @PreAuthorize(AuthConstants.NONE)
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRecord record) {
        Boolean isVerified = userService.verifyEmail(record.email(), record.otp());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RequestResponse("Your email verify successfully", isVerified));
    }

    @PostMapping("/sign-up-email")
    @PreAuthorize(AuthConstants.NONE)
    @Transactional
    public ResponseEntity<?> registerWithEmail(@RequestParam String email) {
        User user = userService.signUpWithEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(
                "Register successfully, please check your email to verify your account.", user));
    }

    @PostMapping("/request-otp")
    @PreAuthorize(AuthConstants.NONE)
    public ResponseEntity<?> requestOtp(@RequestParam String email) {
        userService.requestOtpForLogin(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RequestResponse("OTP has been sent to your email."));
    }

    @PostMapping("/sign-in-email")
    @PreAuthorize(AuthConstants.NONE)
    public ResponseEntity<?> loginWithEmail(@RequestBody EmailSignInRecord emailLoginRecord) {
        TokenDTO dto = userService.signInWithEmail(emailLoginRecord);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse("Sign in successful", dto));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse("Invalid OTP"));
    }

}