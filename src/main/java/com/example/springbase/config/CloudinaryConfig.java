package com.example.springbase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    
    private final String CLOUD_NAME = "drp8xewsm";
    private final String API_KEY = "551326791516423";
    private final String API_SECRET = "qpjOauwbCUF9woJ_W4xKkyPEvK4";

    @Bean 
    public Cloudinary cloudinary(){
        Map<String,String> configMap = new HashMap<>();
        configMap.put("cloud_name", CLOUD_NAME);
        configMap.put("api_key", API_KEY);
        configMap.put("api_secret", API_SECRET);

        return new Cloudinary(configMap);
    }

}
