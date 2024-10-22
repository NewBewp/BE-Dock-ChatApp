package com.example.springbase.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.example.springbase.service.FileUpload;

import java.io.IOException;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUpload {
    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }

}
