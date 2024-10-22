package com.example.springbase.controller;

import com.example.springbase.entity.User;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.service.FileUpload;
import com.example.springbase.service.UserService;
import com.example.springbase.util.AuthConstants;

import java.io.IOException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j

@RestController
@RequestMapping("/api/self")
public class UserController extends GenericController<User, String> {
    @Autowired
    private UserService userService;

    @Autowired
    private FileUpload fileUpload;

    @Override
    public IService<User, String> getService() {
        return userService;
    }

    // @PostMapping("/save-url")
    // @PreAuthorize(AuthConstants.NONE)
    // public String saveURL(@RequestBody String url) {
    // log.info("url: {}", url);
    // return url;
    // }

    // @PostMapping("/upload-avatar")
    // @PreAuthorize(AuthConstants.NONE)
    // public String uploadFile(@RequestParam("image") MultipartFile multipartFile,
    //         Model model) throws IOException {
    //     String imageURL = fileUpload.uploadFile(multipartFile);
    //     model.addAttribute("imageURL", imageURL);
    //     log.info("imageURL:>>>> {}", imageURL);
    //     return imageURL;
    // }

    @PutMapping("/update-avatar/{id}")
    @PreAuthorize(AuthConstants.NONE)
    public String updateAvatar(@PathVariable String id,
            @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        // Upload file và lấy URL
        String imageURL = fileUpload.uploadFile(multipartFile);

        // Cập nhật URL vào cơ sở dữ liệu
        User user = userService.findOne(id);
        if (user != null) {
            user.setAvatarURL(imageURL); // Giả sử bạn có phương thức setAvatarUrl trong User
            userService.save(user); // Lưu người dùng với URL mới
            log.info("Updated avatar URL for user {}: {}", id, imageURL);
            return imageURL; // Trả về URL của avatar
        } else {
            log.error("User not found with ID: {}", id);
            return "User not found";
        }
    }

    // @GetMapping("/{id}")
    // @PreAuthorize(AuthConstants.NONE)
    // public ResponseEntity<User> findOne( @PathVariable String id){
    //     User user = userService.findOne(id);
    //     return ResponseEntity.status(HttpStatus.FOUND).body(user);
    // }

}
