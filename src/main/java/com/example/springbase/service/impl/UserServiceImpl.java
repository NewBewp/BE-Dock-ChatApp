package com.example.springbase.service.impl;

import com.example.springbase.dto.TokenDTO;
import com.example.springbase.dto.request.UserUpdateRequest;
import com.example.springbase.dto.response.UserResponse;
import com.example.springbase.entity.User;
import com.example.springbase.exception.ErrorHandler;
import com.example.springbase.generic.IRepository;
import com.example.springbase.jwt.JwtService;
import com.example.springbase.mapper.UserMapper;
import com.example.springbase.record.EmailSignInRecord;
import com.example.springbase.record.RegisterRecord;
import com.example.springbase.record.SignInRecord;
import com.example.springbase.repository.UserRepository;
import com.example.springbase.service.AbstractService;
import com.example.springbase.service.MailService;
import com.example.springbase.service.UserService;
import com.example.springbase.util.EmailSubjectEnum;
import com.example.springbase.util.TypeMailEnum;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class UserServiceImpl extends AbstractService<User, String> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    @Lazy
    private MailService mailService;

    @Autowired
    private JwtService jwtService;

    @Autowired 
    private UserMapper userMapper;

    // Chứa code otp xác thực với email
    Map<String, String> verifycationCodes = new ConcurrentHashMap<>();

    @Override
    protected IRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && !user.get().getIsDeleted())
            return user.get();
        else
            throw new ErrorHandler(HttpStatus.UNAUTHORIZED, "Account not exist");
    }

    @Override
    public TokenDTO signIn(SignInRecord record) {
        String accessToken = null;

        User user = userRepository.findByUsername(record.username()).orElseThrow(
                () -> new ErrorHandler(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!user.isEmailVerified()) {
            throw new ErrorHandler(HttpStatus.UNAUTHORIZED,
                    "Email not verified. Please verify your email before signing in.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(record.username(), record.password()));
        if (authentication.isAuthenticated()) {
            User account = new User();
            account.setUsername(authentication.getName());
            accessToken = jwtService.generateToken(account);
        }

        // missing refresh token
        if (accessToken != null) {
            return new TokenDTO(accessToken, user.getId());
        }
        return null;
    }

    @Override
    public User signUp(RegisterRecord record) {
        Optional<User> usersOptional = userRepository.findByEmail(record.email());
        if (usersOptional.isPresent()) {
            throw new ErrorHandler(HttpStatus.BAD_REQUEST, "Email already exists.");
        }

        User user = new User();
        user.setUsername(record.username());
        user.setPassword(passwordEncoder.encode(record.password()));
        user.setIsDeleted(false);
        user.setEmail(record.email());
        user.setEmailVerified(false);

        // Tạo otp
        String otp = genarateVericationCode();
        verifycationCodes.put(record.email(), otp);

        mailService.sendWithTemplate(record.email(), otp, EmailSubjectEnum.OTP, TypeMailEnum.OTP);

        return save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new ErrorHandler(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public User getAuthenticatedAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return findByUsername(authentication.getName());
        }
        return null;
    }

    @Override
    public Boolean isSendMailForgetPassword(String email) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ErrorHandler(HttpStatus.NOT_FOUND, "User not found"));
    }

    public String genarateVericationCode() {
        SecureRandom random = new SecureRandom();

        int code = random.nextInt(999999);

        return String.format("%06d", code);
    }

    // Lấy otp xác thực
    public String getVerificationCode(String email) {
        return verifycationCodes.get(email);
    }

    // Xóa otp xác thực
    public void clearVerificationCode(String email) {
        verifycationCodes.remove(email);
    }

    @Override
    public Boolean verifyEmail(String email, String otp) {
        // Kiểm tra có tồn tại mã OTP theo email
        String storedOtp = getVerificationCode(email);
        if (storedOtp == null) {
            throw new ErrorHandler(HttpStatus.BAD_REQUEST, "No OTP found for this email.");
        }
        // So sánh OTP có khớp không ?
        if (!storedOtp.equals(otp)) {
            throw new ErrorHandler(HttpStatus.BAD_REQUEST, "Invalid OTP.");
        }
        // Nếu mã OTP hợp lệ cặp nhật emailVerified của người dùng
        User user = findByEmail(email);
        if (user == null) {
            throw new ErrorHandler(HttpStatus.NOT_FOUND,"User not found");
        }
        user.setEmailVerified(true);
        userRepository.save(user);

        // Xóa mã OTP sau khi xác thực thành công
        clearVerificationCode(email);

        return true;
    }

    @Override
    public User signUpWithEmail(String email) {
        Optional<User> usersOptional = userRepository.findByEmail(email);
        if (usersOptional.isPresent()) {
            throw new ErrorHandler(HttpStatus.BAD_REQUEST, "Email already exsts.");
        }

        User user = new User();
        user.setEmail(email);
        user.setEmailVerified(false); // Chưa xác thực
        user.setIsDeleted(false); // Chưa bị xóa

        String otp = genarateVericationCode();
        verifycationCodes.put(email, otp);

        mailService.sendWithTemplate(email, otp, EmailSubjectEnum.OTP, TypeMailEnum.OTP);

        return save(user);
    }

    @Override
    public TokenDTO signInWithEmail(EmailSignInRecord record) {
        // Kiểm tra mã OTP
        String storedOtp = getVerificationCode(record.email());
        if (storedOtp == null || !storedOtp.equals(record.otp())) {
            throw new ErrorHandler(HttpStatus.UNAUTHORIZED, "Invalid OTP.");
        }

        // Tạo token cho người dùng
        User user = findByEmail(record.email());
        String accessToken = jwtService.generateToken(user);

        // Xóa mã OTP sau khi xác thực thành công
        clearVerificationCode(record.email());

        return new TokenDTO(accessToken, user.getId());
    }

    @Override
    public void requestOtpForLogin(String email) {
        // Kiểm tra xem email có tồn tại không
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ErrorHandler(HttpStatus.UNAUTHORIZED, "Email not registered."));

        // Tạo mã OTP
        String otp = genarateVericationCode();
        verifycationCodes.put(email, otp); // Lưu mã OTP

        // Gửi mã OTP đến email
        mailService.sendWithTemplate(email, otp, EmailSubjectEnum.OTP, TypeMailEnum.OTP);
    }

    @Override
    public UserResponse updateUser (String id, UserUpdateRequest request){
        User user = userRepository.findById(id).orElseThrow(
            () -> new ErrorHandler(HttpStatus.NOT_FOUND, "User not found"));
            if (request.getFirstName() != null) {
                user.setFirstName(request.getFirstName());
            }
            if (request.getLastName() != null) {
                user.setLastName(request.getLastName());
            }
            if (request.getPhoneNumber() != null) {
                user.setPhoneNumber(request.getPhoneNumber());
            }
            if (request.getAvatarURL() != null) {
                user.setAvatarURL(request.getAvatarURL());
            }

        userRepository.save(user);
        return userMapper.torResponse(user);        
    }



}

    
